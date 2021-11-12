package com.example.qlbhbe.service.nhanvien;

import com.example.qlbhbe.controller.request.UpdateNhanVienRequest;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.service.BaseService;


public interface NhanVienService extends BaseService<NhanVien, Long> {

    NhanVien update(long id, NhanVienDTO command);

}
