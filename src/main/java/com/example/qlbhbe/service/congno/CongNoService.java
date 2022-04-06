package com.example.qlbhbe.service.congno;

import com.example.qlbhbe.controller.request.UpdateCongNoRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CongNoService extends BaseService<CongNo, Long> {

    CongNo update(long id, CongNoDTO command);

    CreatedIdResponse create(CongNoDTO command);
    Page<CongNoDTO> search(CongNoDTO command, Pageable pageable) throws Exception;
}
