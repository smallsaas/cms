package com.jfeat.am.module.advertisement.api;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.model.AdLinkRequest;
import com.jfeat.am.module.advertisement.services.domain.model.AdLinkType;
import com.jfeat.am.module.advertisement.services.domain.model.smallProductRequest;
import com.jfeat.am.module.advertisement.services.persistence.model.AdLinkDefinition;
import com.jfeat.am.module.advertisement.services.service.AdLinkDefinitionService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/api/cms/ad/definition")
public class AdLinkDefinitionEndpoint{

    @Resource
    private AdLinkDefinitionService adLinkDefinitionService;

    @Resource
    QueryAdDao queryAdDao;

    @GetMapping
    public Tip listAdLinkDefinition() {
        //封装对象成前端需要的类型
        List<AdLinkDefinition> adLinkList= adLinkDefinitionService.retrieveMasterList();

        List<AdLinkRequest> adLinkRequestList=new ArrayList<>();

        AdLinkRequest functionadLinkRequest=new AdLinkRequest();
        AdLinkRequest productadLinkRequest=new AdLinkRequest();
        AdLinkRequest categoryadLinkRequest=new AdLinkRequest();
        List<AdLinkDefinition> functionChildren = new ArrayList<>();
        List<AdLinkDefinition> productChildren = new ArrayList<>();
        List<AdLinkDefinition> categoryChildren = new ArrayList<>();

        for (AdLinkDefinition adLink:adLinkList) {
           if(adLink.getType()!=null&&adLink.getType().equals(AdLinkType.FUNCTIONLINK)){
               functionChildren.add(adLink);
           }
           //封装产品
            if(adLink.getType()!=null&&adLink.getType().equals(AdLinkType.PRODUCTLINK)){
                List <smallProductRequest> smallProductList= queryAdDao.getProductIdName();
                String url=adLink.getUrl();
                for (smallProductRequest smallProduct:smallProductList) {
                    AdLinkDefinition adLinkDefinition=new AdLinkDefinition();
                    adLinkDefinition.setId(smallProduct.getId()+1000);
                    adLinkDefinition.setName(smallProduct.getName());
                    adLinkDefinition.setUrl(url+smallProduct.getId());
                    productChildren.add(adLinkDefinition);
                }

            }
            //封装分类
            if(adLink.getType()!=null&&adLink.getType().equals(AdLinkType.CATEGORYLINK)){
                List <smallProductRequest> smallProductList= queryAdDao.getCategoryIdName();
                String url=adLink.getUrl();
                for (smallProductRequest smallProduct:smallProductList) {
                    AdLinkDefinition adLinkDefinition=new AdLinkDefinition();
                    adLinkDefinition.setId(smallProduct.getId()+1000);
                    adLinkDefinition.setName(smallProduct.getName());
                    adLinkDefinition.setUrl(url+smallProduct.getId());
                    categoryChildren.add(adLinkDefinition);
                }

            }
        }

        functionadLinkRequest.setChildren(functionChildren);
        functionadLinkRequest.setName("功能链接");
        //此处由于前端组件的原因，会自动读取id +100000设置id不相同
        functionadLinkRequest.setId(AdLinkType.FUNCTIONLINK.longValue()+100000);

        productadLinkRequest.setChildren(productChildren);
        productadLinkRequest.setName("产品链接");
        productadLinkRequest.setId(AdLinkType.PRODUCTLINK.longValue()+100000);

        categoryadLinkRequest.setChildren(categoryChildren);
        categoryadLinkRequest.setName("产品类别链接");
        categoryadLinkRequest.setId(AdLinkType.CATEGORYLINK.longValue()+100000);

        adLinkRequestList.add(functionadLinkRequest);
        adLinkRequestList.add(productadLinkRequest);
        adLinkRequestList.add(categoryadLinkRequest);

        return SuccessTip.create(adLinkRequestList);
    }

    @GetMapping("/{id}")
    public Tip getAdLinkDefinition(@PathVariable Long id) {
        return SuccessTip.create(adLinkDefinitionService.retrieveMaster(id));
    }

    @PostMapping
    public Tip createAdLinkDefinition(@RequestBody AdLinkDefinition entity) {
        return SuccessTip.create(adLinkDefinitionService.createMaster(entity));
    }

    @PutMapping("/{id}")
    public Tip updateAdLinkDefinition(@PathVariable Long id, @RequestBody AdLinkDefinition entity) {
        return SuccessTip.create(adLinkDefinitionService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteAdLinkDefinition(@PathVariable Long id) {
        return SuccessTip.create(adLinkDefinitionService.deleteMaster(id));
    }
}
