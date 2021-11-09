package com.example.qlbhbe.service.cuahang;

import com.example.qlbhbe.controller.request.UpdateCuaHangRequest;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.entity.CuaHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CuaHangService extends BaseService<CuaHang, Long> {

    CuaHang update(long id, CuaHangDTO command);

    Page<CuaHangDTO> search(CuaHangDTO cuaHangDTO, Pageable pageable) throws Exception;
}
