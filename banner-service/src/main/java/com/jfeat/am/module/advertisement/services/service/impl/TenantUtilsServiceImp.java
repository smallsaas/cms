package com.jfeat.am.module.advertisement.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.module.advertisement.services.service.TenantUtilsService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
//import com.jfeat.org.services.persistence.dao.SysOrgMapper;
//import com.jfeat.org.services.persistence.model.SysOrg;
import com.jfeat.users.account.services.gen.persistence.dao.UserAccountMapper;
import com.jfeat.users.account.services.gen.persistence.model.UserAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TenantUtilsServiceImp implements TenantUtilsService {

    @Resource
    UserAccountMapper userAccountMapper;

//    @Resource
//    SysOrgMapper sysOrgMapper;

    @Override
    public Long getCurrentOrgId(Long user) {
        if (user==null || user<=0){
            throw new BusinessException(BusinessCode.BadRequest,"userId错误");
        }
        UserAccount userAccount = userAccountMapper.selectById(user);
        if (userAccount==null){
            throw new BusinessException(BusinessCode.UserNotExisted);
        }
        Long orgId = null;
        if (userAccount.getCurrentOrgId()!=null){
            orgId = userAccount.getCurrentOrgId();
        }else if (userAccount.getOrgId()!=null){
            orgId = userAccount.getOrgId();
        }
        return orgId;
    }


//    public SysOrg getTenantInfo(Long id){
//       return  sysOrgMapper.selectById(id);
//    }
}
