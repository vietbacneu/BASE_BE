package com.example.qlbhbe.service.nhaphangchitiet;

import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.service.BaseService;


public interface NhapHangChiTietService extends BaseService<NhapHangChiTiet, Long> {

    NhapHangChiTiet update(long id, UpdateNhapHangChiTietRequest command);

}
