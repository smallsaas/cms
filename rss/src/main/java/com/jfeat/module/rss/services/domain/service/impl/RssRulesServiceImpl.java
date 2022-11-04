package com.jfeat.module.rss.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.domain.service.RssComponentService;
import com.jfeat.module.rss.services.domain.service.RssImageNameOverModelService;
import com.jfeat.module.rss.services.domain.service.RssRulesService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssRulesServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssRulesService")
public class RssRulesServiceImpl extends CRUDRssRulesServiceImpl implements RssRulesService {


    @Resource
    RssComponentPropService rssComponentPropService;

    @Resource
    RssComponentService rssComponentService;

    @Resource
    RssImageNameOverModelService rssImageNameOverModelService;

    @Override
    protected String entityName() {
        return "RssRules";
    }


    @Override
    public Integer parseRssByCommon(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {

        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        String defaultCssName = "";
        RssComponent rssComponent = new RssComponent();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules : rssRulesList) {

            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    String symbol = m.group(1);
                    type = rssRules.getName();
                    decollator = rssRules.getDecollator();
                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;


            }
        }


        // 判断是否有组件风格
        String albumPattern = ".*<<(.*)>>.*";
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<<".concat(m.group(1)).concat(">>");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setCssName(m.group(1));
                String componentStyle = "<".concat(m.group(1)).concat(">");
                data = data.substring(componentStyle.length()).strip();
            }
        } else if (defaultCssName != null && !defaultCssName.equals("")) {
            rssComponent.setCssName(defaultCssName);
        }


        List<String> componentPropData = new ArrayList<>();
        if (decollator != null && !decollator.equals("")) {
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        } else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i = 0; i < componentPropData.size(); i++) {
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }


    @Override
    public Integer parseRssByTitle(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {
        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        String defaultCssName = "";
        RssComponent rssComponent = new RssComponent();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (int i = 6; i > 0; i--) {

            String symbolNum = "#{".concat(String.valueOf(i)).concat("}");

            String startSymbol = "^".concat("(").concat(symbolNum).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    String symbol = m.group(1);
                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                defaultCssName = "H".concat(String.valueOf(i));
                break;
            }
        }


        // 判断是否有组件风格
        String albumPattern = ".*<<(.*)>>.*";
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<<".concat(m.group(1)).concat(">>");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setCssName(m.group(1));
                String componentStyle = "<".concat(m.group(1)).concat(">");
                data = data.substring(componentStyle.length()).strip();
            }
        } else if (defaultCssName != null && !defaultCssName.equals("")) {
            rssComponent.setCssName(defaultCssName);
        }


        List<String> componentPropData = new ArrayList<>();
        if (decollator != null && !decollator.equals("")) {
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        } else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i = 0; i < componentPropData.size(); i++) {
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }

    @Override
    public Integer parseRssByBulleList(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {
        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        String defaultCssName = "";
        String icon = "";
        RssComponent rssComponent = new RssComponent();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules : rssRulesList) {

            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    String symbol = m.group(1);
                    icon = m.group(2);
                    type = rssRules.getName();
                    decollator = rssRules.getDecollator();
                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;


            }
        }


        // 判断是否有组件风格
        String albumPattern = ".*<<(.*)>>.*";
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<<".concat(m.group(1)).concat(">>");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setCssName(m.group(1));
                String componentStyle = "<".concat(m.group(1)).concat(">");
                data = data.substring(componentStyle.length()).strip();
            }
        } else if (defaultCssName != null && !defaultCssName.equals("")) {
            rssComponent.setCssName(defaultCssName);
        }

        if (icon != null && !icon.equals("")) {
            rssComponent.setComponentStyleValue(icon);
        }


        List<String> componentPropData = new ArrayList<>();
        if (decollator != null && !decollator.equals("")) {
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        } else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i = 0; i < componentPropData.size(); i++) {
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }

    @Override
    public Integer parseRssByLink(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {
        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        String defaultCssName = "";
        String title = "";
        String link = "";

        RssComponent rssComponent = new RssComponent();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules : rssRulesList) {

            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    String symbol = m.group(1);
                    type = rssRules.getName();
                    decollator = rssRules.getDecollator();

                    title = m.group(2);
                    link = m.group(3);

                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;


            }
        }


        // 判断是否有组件风格
        String albumPattern = ".*<<(.*)>>.*";
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<<".concat(m.group(1)).concat(">>");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setCssName(m.group(1));
                String componentStyle = "<".concat(m.group(1)).concat(">");
                data = data.substring(componentStyle.length()).strip();
            }
        } else if (defaultCssName != null && !defaultCssName.equals("")) {
            rssComponent.setCssName(defaultCssName);
        }


        List<String> componentPropData = new ArrayList<>();

        componentPropData.add(title);
        componentPropData.add(link);

        if (decollator != null && !decollator.equals("")) {
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        } else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i = 0; i < componentPropData.size(); i++) {
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }

    @Override
    public Integer parseRssByRichText(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {
        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        String defaultCssName = "";
        RssComponent rssComponent = new RssComponent();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules : rssRulesList) {

            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    String symbol = m.group(1);
                    type = rssRules.getName();
                    decollator = rssRules.getDecollator();
                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;


            }
        }


        Integer textNumber = 200;

        String Numberpattern = "^/(\\d+).*";
        boolean isMatchNumber = Pattern.matches(Numberpattern, data);
        if (isMatchNumber) {
            Pattern r = Pattern.compile(Numberpattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setCssName(m.group(1));
                try {
                    textNumber = Integer.parseInt(m.group(1));
                } catch (Exception e) {
                    textNumber = 200;
                }

                String rmString = "/".concat(m.group(1));
                String startToMid = data.substring(0, data.indexOf(rmString));
                String midToEnd = data.substring(startToMid.length() + rmString.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }


        // 判断是否有组件风格
        String albumPattern = ".*<<(.*)>>.*";
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<<".concat(m.group(1)).concat(">>");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        String propStyle = null;
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                propStyle = m.group(1);

                String rmString = "<".concat(m.group(1)).concat(">");
                String startToMid = data.substring(0, data.indexOf(rmString));
                String midToEnd = data.substring(startToMid.length() + rmString.length());
                data = startToMid.concat(midToEnd).strip();
            }
        } else if (defaultCssName != null && !defaultCssName.equals("")) {
            rssComponent.setCssName(defaultCssName);
        }


//        风格符
        List<String> componentPropData = new ArrayList<>();
        if (decollator != null && !decollator.equals("")) {
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        } else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i = 0; i < componentPropData.size(); i++) {
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropStyle(propStyle);
            rssComponentProp.setPropLimit(String.valueOf(textNumber));
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }

    @Override
    public Integer parseRssByTable(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {


        String type = "table";
        Integer affect = 0;
        Long componentId = null;
        List<RssComponentProp> componentPropData = new ArrayList<>();
        RssComponent rssComponent = new RssComponent();

        while (!queue.isEmpty() && type.equals("table")) {
            String data = queue.peek();
            String decollator = "";
            String defaultCssName = "";

            String tableType = "";

            Boolean flag = true;

            //        判断Rss类型
            for (RssRules rssRules : rssRulesList) {

                String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
                boolean isSymbol = Pattern.matches(startSymbol, data.strip());

                if (isSymbol) {
                    Pattern r = Pattern.compile(startSymbol);
                    Matcher m = r.matcher(data);
                    if (m.find()) {
                        type = rssRules.getHandMethod();
                        tableType = rssRules.getName();
                        if (rssRules.getHandMethod().equals("table")) {
                            queue.poll();
                            flag = false;
                        }
                        String symbol = m.group(1);
                        if (type != null && symbol.length() > 0) {
                            data = data.substring(symbol.length()).strip();
                        }
                    }
                    break;
                }
            }

            if (flag) {
                break;
            }


            // 判断是否有组件风格
            String albumPattern = ".*<<(.*)>>.*";
            boolean isMatchAlbum = Pattern.matches(albumPattern, data);
            if (isMatchAlbum) {
                Pattern r = Pattern.compile(albumPattern);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    rssComponent.setComponentStyle(m.group(1));
                    String componentStyle = "<<".concat(m.group(1)).concat(">>");
                    String startToMid = data.substring(0, data.indexOf(componentStyle));
                    String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                    data = startToMid.concat(midToEnd).strip();
                }
            }


            // 判断是否有排列比例
            String arrangePattern = ".*\\[(.*)\\].*";
            boolean isMatchArrange = Pattern.matches(arrangePattern, data);
            if (isMatchArrange) {
                Pattern r = Pattern.compile(arrangePattern);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    rssComponent.setComponentArrangementValue(m.group(1));
                    String componentStyle = "[".concat(m.group(1)).concat("]");
                    String startToMid = data.substring(0, data.indexOf(componentStyle));
                    String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                    data = startToMid.concat(midToEnd).strip();
                }
            }


//        判断是否有风格
            String pattern = ".*<(.*)>.*";
            String propStyle = "";
            boolean isMatch = Pattern.matches(pattern, data);
            if (isMatch) {
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(data);
                if (m.find()) {
                    propStyle = m.group(1);
                    String componentStyle = "<".concat(m.group(1)).concat(">");
                    data = data.substring(componentStyle.length()).strip();
                }
            }

            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setPropDefaultValue(data);
            rssComponentProp.setOptionName(tableType);
            rssComponentProp.setPropStyle(propStyle);
            componentPropData.add(rssComponentProp);

            rssComponent.setComponentName(type);
            rssComponent.setComponentType(type);
            rssComponent.setRssItemId(rssItem.getId());
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();
        if (componentPropData!=null){
            for (RssComponentProp rssComponentProp: componentPropData) {
                rssComponentProp.setComponentId(componentId);
                affect += rssComponentPropService.createMaster(rssComponentProp);
            }
        }

        return affect;
    }


    public Integer parseRssByImage(Queue<String> queue, RssItem rssItem, List<RssRules> rssRulesList) {
        Integer affect = 0;

//        判读是什么类型
        String type = "title";
        String data = queue.poll();


        if (data == null) {
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules : rssRulesList) {
            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol) {
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
//                去除前面标识符
                if (m.find()) {
                    String symbol = m.group(1);
                    type = rssRules.getName();
                    if (type != null && symbol.length() > 0) {
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;
            }
        }


        List<JSONObject> imageCollectionJsonList = null;

        //        判断是否有图片集
        String albumPattern = ".*<<(.*)>>.*";

//        相册名
        String imageCollectionName = null;
        boolean isMatchAlbum = Pattern.matches(albumPattern, data);
        if (isMatchAlbum) {
            Pattern r = Pattern.compile(albumPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                imageCollectionName = m.group(1);
//                查看图片集图片列表
                Map<String, List<JSONObject>> allRssImageToList = rssImageNameOverModelService.getAllRssImageToList();

                if (allRssImageToList != null && allRssImageToList.containsKey(imageCollectionName) && allRssImageToList.get(imageCollectionName) != null) {
                    imageCollectionJsonList = allRssImageToList.get(imageCollectionName);
                }
                String componentStyle = "<<".concat(m.group(1)).concat(">>");


                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }

//        将json 格式转 String
        List<String> imageCollectionList = null;
        if (imageCollectionJsonList != null) {
            imageCollectionList = imageCollectionJsonList.stream().map(JSON::toString).collect(Collectors.toList());
        }


        //        判断是否有容器风格风格
        String containerStylePattern = ".*<@(.*)>.*";
        String containerStyle = null;
//        添加单图片风格
        boolean isMatchContainerStyle = Pattern.matches(containerStylePattern, data);
        if (isMatchContainerStyle) {
            Pattern r = Pattern.compile(containerStylePattern);
            Matcher m = r.matcher(data);
            if (m.find()) {


                containerStyle = m.group(1);

                String componentStyle = "<@".concat(m.group(1)).concat(">");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }


//        判断是否有风格
        String stylePattern = ".*<(.*)>.*";
        String singeImageStyle = null;
//        添加单图片风格
        boolean isMatchStyle = Pattern.matches(stylePattern, data);
        if (isMatchStyle) {
            Pattern r = Pattern.compile(stylePattern);
            Matcher m = r.matcher(data);
            if (m.find()) {

                singeImageStyle = m.group(1);

                String componentStyle = "<".concat(m.group(1)).concat(">");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }


        List<String> arrangementList = new ArrayList<>();

        //        判断是否有排列方式
        String iamgeArrangePattern = ".*\\[\\[(.*)\\]\\].*";
        boolean isMatchImageArrange = Pattern.matches(iamgeArrangePattern, data);
        if (isMatchImageArrange) {
            Pattern r = Pattern.compile(iamgeArrangePattern);
            Matcher m = r.matcher(data);
            if (m.find()) {

                String arrangement = m.group(1);
//                排列方式列表
                arrangementList = Arrays.asList(arrangement.split(";"));


                String componentStyle = "[".concat(m.group(1)).concat("]");
                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }


        //        判断是否有单张图片
        List<String> singleImageList = null;
        String imageUrlPattern = ".*\\((.*)\\).*";
        boolean isMatchImageUrl = Pattern.matches(imageUrlPattern, data);
        if (isMatchImageUrl) {
            Pattern r = Pattern.compile(imageUrlPattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                String singleImage = m.group(1);

//                直接给图片列表
                String[] split = singleImage.split(",");
                singleImageList = Arrays.asList(split);

                String componentStyle = "(".concat(m.group(1)).concat(")");

                String startToMid = data.substring(0, data.indexOf(componentStyle));
                String midToEnd = data.substring(startToMid.length() + componentStyle.length());
                data = startToMid.concat(midToEnd).strip();
            }
        }


        Queue<String> stringQueue = new LinkedList<>();
        if (singleImageList != null) {
            stringQueue.addAll(singleImageList);
        }
        if (imageCollectionList != null) {
            stringQueue.addAll(imageCollectionList);
        }


        if (arrangementList == null || arrangementList.size() == 0) {
            List<RssComponentProp> rssComponentPropList = new ArrayList<>();
            RssComponent rssComponent = new RssComponent();
            rssComponent.setComponentStyle(containerStyle);
            rssComponent.setAlbumName(imageCollectionName);
            rssComponent.setRssItemId(rssItem.getId());
            rssComponent.setComponentType(type);

            while (!stringQueue.isEmpty()) {
                RssComponentProp rssComponentProp = new RssComponentProp();
                rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                rssComponentProp.setPropStyle(singeImageStyle);
                rssComponentPropList.add(rssComponentProp);
            }

            //                添加记录
            affect += rssComponentService.createMaster(rssComponent);
            Long componentId = rssComponent.getId();

            for (RssComponentProp item : rssComponentPropList) {
                item.setComponentId(componentId);
            }
            affect += rssComponentPropService.batchRssComponentProp(rssComponentPropList);


        } else {
            for (String arrangementItem : arrangementList) {


                RssComponent rssComponent = new RssComponent();

                String numberImage = getMatchValue(arrangementItem, ".*\\[(.*)\\].*");
                String imageHigh = getMatchValue(arrangementItem, ".*\\{(.*)\\}.*");

                if (imageHigh!=null&& imageHigh.contains(":")){
                    String ratio[] = imageHigh.split(":");
                    try {
                        Double left = Double.parseDouble(ratio[0]);
                        Double right = Double.parseDouble(ratio[1]);
                        Double result = left/right;
                        imageHigh = "[".concat(String.valueOf(result)).concat("]");

                    }catch (Exception e){
                        imageHigh = "[1]";
                    }
                }

//                设置排列比例 或者数量
                rssComponent.setComponentArrangementValue(numberImage);
                rssComponent.setRssItemId(rssItem.getId());
                rssComponent.setComponentType(type);
//                设置容器高度
                rssComponent.setComponentLimit(imageHigh);
                rssComponent.setComponentStyle(containerStyle);
                rssComponent.setAlbumName(imageCollectionName);

                List<RssComponentProp> rssComponentPropList = new ArrayList<>();
//                如果为空就跳出
                if (stringQueue.isEmpty()) {
                    break;
                }

                if (arrangementItem.startsWith("=-")) {
//                    设置排列方式
                    rssComponent.setComponentArrangement("=-");
                    for (int i = 0; i < 3 && !stringQueue.isEmpty(); i++) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }


                } else if (arrangementItem.startsWith("-=")) {
//                    设置排列方式
                    rssComponent.setComponentArrangement("-=");
                    for (int i = 0; i < 3 && !stringQueue.isEmpty(); i++) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }

                } else if (arrangementItem.startsWith("---")) {
                    int imageNumber = 4;

                    if (numberImage != null) {
                        imageNumber = numberImage.split(",").length;
                    }
//                    设置排列方式
                    rssComponent.setComponentArrangement("---");
                    for (int i = 0; i < imageNumber && !stringQueue.isEmpty(); i++) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }
                } else if (arrangementItem.startsWith("|||")) {
                    int imageNumber = 4;

                    if (numberImage != null) {
                        try {
                            imageNumber = Integer.parseInt(numberImage);
                        } catch (Exception e) {
                            imageNumber = 4;
                        }

                    }
//                    设置排列方式
                    rssComponent.setComponentArrangement("|||");
                    for (int i = 0; i < imageNumber && !stringQueue.isEmpty(); i++) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }

                } else if (arrangementItem.startsWith("#")) {

                    int imageNumber = 9;

                    int rowNumber = 3;
                    int columnNumber =3;

                    if (numberImage != null) {

//                        第一个是列 第二个是行
                        String numberStr[] = numberImage.split(",");
                        if (numberStr!=null&&numberStr.length>0){
                            try {
                                columnNumber = Integer.parseInt(numberStr[0]);
                            } catch (Exception e) {
                                columnNumber = 3;
                            }

                            if (numberStr.length>1){
                                try {
                                    rowNumber = Integer.parseInt(numberStr[1]);
                                } catch (Exception e) {
                                    rowNumber = 3;
                                }
                            }

                        }

                        imageNumber = columnNumber*rowNumber;

                    }
//                    设置排列方式
                    rssComponent.setComponentArrangement("#");
                    rssComponent.setComponentArrangementValue(String.valueOf(columnNumber));
                    for (int i = 0; i < imageNumber && !stringQueue.isEmpty(); i++) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }
                } else {
                    while (!stringQueue.isEmpty()) {
                        RssComponentProp rssComponentProp = new RssComponentProp();
                        rssComponentProp.setPropDefaultValue(stringQueue.poll());
//                        设置单图片风格
                        rssComponentProp.setPropStyle(singeImageStyle);
                        rssComponentPropList.add(rssComponentProp);
                    }
                }


//                添加记录
                affect += rssComponentService.createMaster(rssComponent);
                Long componentId = rssComponent.getId();

                for (RssComponentProp item : rssComponentPropList) {
                    item.setComponentId(componentId);
                }
                affect += rssComponentPropService.batchRssComponentProp(rssComponentPropList);
            }
        }


        return affect;
    }


    private static String getMatchValue(String data, String pattern) {
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()) {
                return m.group(1);
            }
        }
        return null;
    }

}
