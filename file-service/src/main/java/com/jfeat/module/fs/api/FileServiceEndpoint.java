package com.jfeat.module.fs.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.util.StrKit;
import com.jfeat.crud.core.properties.AmProperties;
import com.jfeat.crud.core.util.HttpKit;
import com.jfeat.module.fs.service.LoadFileCodeService;
import com.jfeat.module.fs.util.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by jackyhuang on 2017/7/4.
 */
@Api("CMS-文件上传")
@RestController
public class FileServiceEndpoint {
    protected static final Logger logger = LoggerFactory.getLogger(FileServiceEndpoint.class);

    @Autowired
    private AmProperties amProperties;

    @Autowired
    LoadFileCodeService loadFileCodeService;

    @ApiOperation(value = "获取下载码",response = String.class,notes = "登陆后自动生成的一个下载码")
    @ApiParam(name = "name", value = "文件名称")
    @GetMapping("/api/fs/dlcode")
    public Tip getCode(@RequestParam String name) {
        String code = loadFileCodeService.genAndGetCode(name);
        return SuccessTip.create(code);
    }

    /**
     * form-data 方式上传图片
     * @param picture
     * @return
     */
    @ApiOperation(value = "multipart方式上传图片",response = FileInfo.class)
    @PostMapping("/api/fs/uploadx")
    @ResponseBody
    public Tip formUpload(@RequestHeader("authorization") String token,
                          @RequestParam(name = "blur", defaultValue = "false") Boolean blur,
                          @RequestPart("file") MultipartFile picture) {
        String originalFileName = picture.getOriginalFilename();
        String extensionName= FilenameUtils.getExtension(originalFileName);
        String pictureName = UUID.randomUUID().toString() + "." + extensionName;
        String blurryName = "";
        try {
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath + pictureName);
            target.setReadable(true);
            FileUtils.copyInputStreamToFile(picture.getInputStream(), target);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
            File reducedFile = ImageUtil.reduce(target);
            logger.info("file reduced to: {}", reducedFile.getAbsolutePath());
            pictureName = reducedFile.getName();
            if (blur) {
                File blurryFile = ImageUtil.reduce(ImageUtil.gaos(target));
                blurryFile.setReadable(true);
                blurryName = blurryFile.getName();
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }
        return SuccessTip.create(FileInfo.create(getFileHost(), pictureName, blurryName));
    }

    /**
     * 数据格式
     * data:image/jpeg;base64,/9j/4AAQSkZJRgABAQ
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "Base64格式上传图片",response = FileInfo.class)
    @PostMapping("/api/fs/upload64")
    @ResponseBody
    public Tip base64Upload(@RequestHeader("authorization") String token,
                            @RequestParam(name = "blur", defaultValue = "false") Boolean blur) throws IOException {
        String base64Data = IOUtils.toString(HttpKit.getRequest().getInputStream(), "UTF-8");
        if (StrKit.isBlank(base64Data)) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        String dataPrix = "";
        String data = "";
        String[] d = base64Data.split("base64,");
        if (d != null && d.length == 2) {
            dataPrix = d[0];
            data = d[1];
        } else {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            suffix = ".png";
        } else {
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        byte[] dataBytes = Base64Utils.decodeFromString(data);

        String pictureName = UUID.randomUUID().toString() + suffix;
        String blurryName = "";
        try{
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath, pictureName);
            target.setReadable(true);
            FileUtils.writeByteArrayToFile(target, dataBytes);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
            File reducedFile = ImageUtil.reduce(target);
            logger.info("file reduced to: {}", reducedFile.getAbsolutePath());
            pictureName = reducedFile.getName();
            if (blur) {
                File blurryFile = ImageUtil.reduce(ImageUtil.gaos(target));
                blurryFile.setReadable(true);
                blurryName = blurryFile.getName();
            }
        }catch(Exception ee){
            throw new BusinessException(BusinessCode.UploadFileError);
        }

        return SuccessTip.create(FileInfo.create(getFileHost(), pictureName, blurryName));
    }

    @ApiOperation(value = "上传文件",response = FileInfo.class)
    @PostMapping("/api/fs/uploadfile")
    @ResponseBody
    public Tip FileUpload(@RequestHeader("authorization") String token,
                          @RequestPart("file") MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            throw new RuntimeException("file is empty");
        }
        String originalFileName = file.getOriginalFilename();
        String extensionName= FilenameUtils.getExtension(originalFileName);
        Long fileSize = file.getSize();
        String fileName = UUID.randomUUID().toString() + "." + extensionName;
        String path = null;
        try {
            String fileSavePath = getFileUploadPath();
            File target = new File(fileSavePath + fileName);
            path = target.getCanonicalPath();
            target.setReadable(true);
            FileUtils.copyInputStreamToFile(file.getInputStream(), target);
            logger.info("file uploaded to: {}", target.getAbsolutePath());
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.UploadFileError);
        }
        return SuccessTip.create(FileInfo.create(getFileHost(), fileName, extensionName, originalFileName, fileSize, path));
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/api/pub/fs/loadfile")
    @ResponseBody
    public void loadFile(@RequestParam(required = true) String name,
                         @RequestParam(required = true) String code) throws IOException {
        if (loadFileCodeService.checkCode(code) == false) {
            throw new BusinessException(BusinessCode.BadRequest);
        }

        //获得rootPath
        String rootPath = getFileUploadPath();
        //拼接绝对路径(目录)
        String absolutePath = rootPath + name;
        //获得资源对象
        FileSystemResource fsr = new FileSystemResource(absolutePath);

        //输出文件
        final int buffInt = 1024;
        byte[] buff = new byte[buffInt];
        OutputStream os = null;
        BufferedInputStream bis = null;
        try { //NOSONAR
            HttpServletResponse response = HttpKit.getResponse();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; " + name);
            os = response.getOutputStream();
            bis = new BufferedInputStream(fsr.getInputStream());
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            if (bis != null) {
                bis.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    private String getFileHost() {
        String fileHost = amProperties.getFileHost();
        String host = fileHost;
        String tenant = JWTKit.getOrgId() + "";
        if (!fileHost.endsWith("/")) {
            host = host + "/" + tenant;
        }
        else {
            host = host + tenant;
        }
        return host;
    }

    private String getFileUploadPath() {
        String fileSavePath = amProperties.getFileUploadPath();
        String uploadPath = fileSavePath;
        String tenant = JWTKit.getOrgId() + "";
        if (!fileSavePath.endsWith(File.separator)) {
            uploadPath = uploadPath + File.separator + tenant + File.separator;
        }
        else {
            uploadPath = uploadPath + tenant + File.separator;
        }

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return uploadPath;
    }
}
