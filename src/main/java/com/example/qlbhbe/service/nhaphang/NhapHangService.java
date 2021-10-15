package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NhapHangService extends BaseService<NhapHang, Long> {

    NhapHang update(long id, UpdateNhapHangRequest command);

    Page<NhapHangDTO> search(NhapHangDTO command, Pageable pageable) throws Exception;

}
