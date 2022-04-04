package com.example.qlbhbe.service.congno;

import com.example.qlbhbe.controller.request.UpdateCongNoRequest;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import com.example.qlbhbe.service.BaseService;


public interface CongNoService extends BaseService<CongNo, Long> {

    CongNo update(long id, CongNoDTO command);

}
