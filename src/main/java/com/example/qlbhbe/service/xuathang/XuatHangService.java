package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.service.BaseService;


public interface XuatHangService extends BaseService<XuatHang, Long> {

    XuatHang update(long id, UpdateXuatHangRequest command);

}
