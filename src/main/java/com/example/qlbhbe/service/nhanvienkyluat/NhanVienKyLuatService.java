package com.example.qlbhbe.service.nhanvienkyluat;

import com.example.qlbhbe.controller.request.UpdateNhanVienKyLuatRequest;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import com.example.qlbhbe.service.BaseService;


public interface NhanVienKyLuatService extends BaseService<NhanVienKyLuat, Long> {

    NhanVienKyLuat update(long id, NhanVienKyLuatDTO command);

}
