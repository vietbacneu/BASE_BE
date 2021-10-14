package com.example.qlbhbe.service.xuathangchitiet;

import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.service.BaseService;


public interface XuatHangChiTietService extends BaseService<XuatHangChiTiet, Long> {

    XuatHangChiTiet update(long id, UpdateXuatHangChiTietRequest command);

}
