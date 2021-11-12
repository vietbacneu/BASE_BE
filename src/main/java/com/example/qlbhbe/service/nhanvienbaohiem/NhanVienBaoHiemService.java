package com.example.qlbhbe.service.nhanvienbaohiem;

import com.example.qlbhbe.controller.request.UpdateNhanVienBaoHiemRequest;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import com.example.qlbhbe.service.BaseService;


public interface NhanVienBaoHiemService extends BaseService<NhanVienBaoHiem, Long> {

    NhanVienBaoHiem update(long id, NhanVienBaoHiemDTO command);

}
