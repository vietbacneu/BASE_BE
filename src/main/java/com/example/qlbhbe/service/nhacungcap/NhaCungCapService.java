package com.example.qlbhbe.service.nhacungcap;

import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.entity.NhaCungCap;
import com.example.qlbhbe.service.BaseService;


public interface NhaCungCapService extends BaseService<NhaCungCap, Long> {

    NhaCungCap update(long id, UpdateNhaCungCapRequest command);

}
