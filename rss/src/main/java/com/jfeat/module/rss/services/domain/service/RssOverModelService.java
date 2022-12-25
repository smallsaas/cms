package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssOverModelService;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssOverModelService extends CRUDRssOverModelService {


//    添加rss
    Integer createRss(RssModel rss, List<RssItem> rssItemList);

//    删除rss
    Integer deleteRss(Long id);

    Integer updateRss(RssRecord rssRecord);

//    更新rss
    Integer updateRssRecord(RssRecord rssRecord);

//    更新解析rss
    Integer updateAndParseRss(RssRecord rssRecord);


//    添加css
    List<RssRecord> andCss(List<RssRecord> recordList);


//    解析上传的rss文件
    String uploadRssFile(MultipartFile file);


//    复制rss
    Integer copyRss(Long id);


}