package com.example.qlbhbe.service.nhanvienkhenthuong;

import com.example.qlbhbe.controller.request.UpdateNhanVienKhenThuongRequest;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import com.example.qlbhbe.service.BaseService;


public interface NhanVienKhenThuongService extends BaseService<NhanVienKhenThuong, Long> {

    NhanVienKhenThuong update(long id, NhanVienKhenThuongDTO command);

}
