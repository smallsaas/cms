package com.jfeat.module.feedback.services.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jfeat.module.feedback.services.service.NFTWispOrderService;
import com.jfeat.module.smallsaas.ticket.services.gen.persistence.dao.ComplainRecordMapper;
import com.jfeat.module.smallsaas.ticket.services.gen.persistence.model.complainrecord.ComplainRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("wispOrderService")
public class NFTWispOrderServiceImpl implements NFTWispOrderService {

    @Resource
    private ComplainRecordMapper complainRecordMapper;



    @Override
    public Page<ComplainRecord> queryComplainList(Integer pageNum, Integer pageSize, String requestType, String status, String title, String phone) {
        Page<ComplainRecord> complainRecordPage = new LambdaQueryChainWrapper<>(complainRecordMapper)
                .eq(ComplainRecord::getRequestType, requestType)
                .like(StringUtils.isNotBlank(status), ComplainRecord::getStatus, status)
                .like(StringUtils.isNotBlank(title), ComplainRecord::getTitle, title)
                .orderByDesc(ComplainRecord::getCreateTime)
                .page(new Page<>(pageNum, pageSize));

        //List<ComplainRecord> complainRecords = complainRecordPage.getRecords();

   /*     var playerIds = complainRecords.stream().map(ComplainRecord::getComplainantId).collect(Collectors.toList());
        var playerMap = playerService.queryByIds(playerIds)
                .stream()
                .collect(Collectors.toMap(Player::getId, JSON::toJSONString));

        List<ComplainRecord> complainDetailRecords = complainRecords
                .stream()
                .map(complainRecord -> COMPLAIN_DETAIL_RECORD_CONVERTER.toComplainDetailRecord(complainRecord)
                        .setPlayer(JSON.parseObject(playerMap.get(complainRecord.getComplainantId()), Player.class)))
                .collect(Collectors.toList());*/

        return complainRecordPage;
    }


    @Override
    public ComplainRecord managerComplainDetailRecord(Long id) {
        ComplainRecord complainRecord = complainRecordMapper.selectById(id);
        return complainRecord;
    }


}
