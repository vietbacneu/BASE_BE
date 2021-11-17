package com.example.qlbhbe.service.chamcong;

import com.example.qlbhbe.controller.request.UpdateChamCongRequest;
import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface ChamCongService extends BaseService<ChamCong, Long> {

    ChamCong update(long id, ChamCongDTO command);

    public Page<ChamCongDTO> search(@RequestBody(required = false) ChamCongDTO command, @PageableDefault Pageable pageable) throws Exception;

     Page<ChamCongDTO> searchPhieuLuong(@RequestBody(required = false) ChamCongDTO command, @PageableDefault Pageable pageable) throws Exception;
}
