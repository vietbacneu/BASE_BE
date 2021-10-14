package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.service.BaseService;


public interface NhapHangService extends BaseService<NhapHang, Long> {

    NhapHang update(long id, UpdateNhapHangRequest command);

}
