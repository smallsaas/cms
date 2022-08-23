package com.jfeat.module.feedback.services.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface NFTWispOrderService {


    Page<ComplainRecord> queryComplainList(Integer pageNum, Integer pageSize, String requestType, String status, String title, String phone);

    ComplainRecord managerComplainDetailRecord(Long id);
}