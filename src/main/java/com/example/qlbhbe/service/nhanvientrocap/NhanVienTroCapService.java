package com.example.qlbhbe.service.nhanvientrocap;

import com.example.qlbhbe.controller.request.UpdateNhanVienTroCapRequest;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.service.BaseService;


public interface NhanVienTroCapService extends BaseService<NhanVienTroCap, Long> {

    NhanVienTroCap update(long id, UpdateNhanVienTroCapRequest command);

}
