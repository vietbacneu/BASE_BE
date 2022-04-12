package com.example.qlbhbe.service.thucdon;

import com.example.qlbhbe.controller.request.UpdateThucDonRequest;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.dto.ThucDonDTO;
import com.example.qlbhbe.entity.ThucDon;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface ThucDonService extends BaseService<ThucDon, Long> {

    ThucDon update(long id, ThucDonDTO command);
    public Page<ThucDonDTO> search(@RequestBody ThucDonDTO params, @PageableDefault Pageable pageable) throws Exception;
}
