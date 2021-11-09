package com.example.qlbhbe.service.khachhang;

import com.example.qlbhbe.controller.request.UpdateKhachHangRequest;
import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.entity.KhachHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface KhachHangService extends BaseService<KhachHang, Long> {

    KhachHang update(long id, KhachHangDTO command);

     Page<KhachHangDTO> search(KhachHangDTO command, Pageable pageable) throws Exception;

}
