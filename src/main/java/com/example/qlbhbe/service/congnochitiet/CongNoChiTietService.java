package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.controller.request.UpdateCongNoChiTietRequest;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface CongNoChiTietService extends BaseService<CongNoChiTiet, Long> {

    CongNoChiTiet update(long id);

    public Page<CongNoChiTietDTO> search(@RequestBody CongNoChiTietDTO params, @PageableDefault Pageable pageable) throws Exception;
}
