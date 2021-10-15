package com.example.qlbhbe.service.sanpham;

import com.example.qlbhbe.controller.request.UpdateSanPhamRequest;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SanPhamService extends BaseService<SanPham, Long> {

    SanPham update(long id, UpdateSanPhamRequest command);

    Page<SanPhamDTO> search(SanPhamDTO command, Pageable pageable) throws Exception;
}
