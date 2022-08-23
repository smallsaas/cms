package com.jfeat.module.feedback.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.feedback.services.gen.crud.service.CRUDComplainRecordService;
import com.jfeat.module.feedback.services.gen.persistence.dao.ComplainRecordMapper;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDComplainRecordService
 * @author Code generator
 * @since 2022-08-12
 */

@Service
public class CRUDComplainRecordServiceImpl  extends CRUDServiceOnlyImpl<ComplainRecord> implements CRUDComplainRecordService {





        @Resource
        protected ComplainRecordMapper complainRecordMapper;

        @Override
        protected BaseMapper<ComplainRecord> getMasterMapper() {
                return complainRecordMapper;
        }







}


