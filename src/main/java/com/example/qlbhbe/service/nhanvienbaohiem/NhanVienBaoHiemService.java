package com.example.qlbhbe.service.nhanvienbaohiem;

import com.example.qlbhbe.controller.request.UpdateNhanVienBaoHiemRequest;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface NhanVienBaoHiemService extends BaseService<NhanVienBaoHiem, Long> {

    NhanVienBaoHiem update(long id, NhanVienBaoHiemDTO command);

    public Page<NhanVienBaoHiemDTO> search(@RequestBody(required = false) NhanVienBaoHiemDTO command, @PageableDefault Pageable pageable) throws Exception;

}
