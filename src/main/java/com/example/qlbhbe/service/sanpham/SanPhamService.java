package com.example.qlbhbe.service.sanpham;

import com.example.qlbhbe.controller.request.UpdateSanPhamRequest;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.service.BaseService;


public interface SanPhamService extends BaseService<SanPham, Long> {

    SanPham update(long id, UpdateSanPhamRequest command);

}
