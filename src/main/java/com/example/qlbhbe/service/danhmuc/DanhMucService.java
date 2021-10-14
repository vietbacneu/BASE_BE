package com.example.qlbhbe.service.danhmuc;

import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.service.BaseService;


public interface DanhMucService extends BaseService<DanhMuc, Long> {

    DanhMuc update(long id, UpdateDanhMucRequest command);

}
