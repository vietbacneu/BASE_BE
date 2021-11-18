package com.example.qlbhbe.service.nhanvien;

import com.example.qlbhbe.controller.request.UpdateNhanVienRequest;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


public interface NhanVienService extends BaseService<NhanVien, Long> {

    NhanVien update(long id, NhanVienDTO command);

    Page<NhanVienDTO> search(@RequestBody(required = false) NhanVienDTO command, @PageableDefault Pageable pageable) throws Exception;

    public Map<String,String> exportNhanVien(@RequestBody(required = false) NhanVienDTO command) throws Exception;

    public Page<NhanVienDTO> danhGia(@RequestBody(required = false) NhanVienDTO command, @PageableDefault Pageable pageable) throws Exception;

    public Map<String,String> exportDanhGia(@RequestBody(required = false) NhanVienDTO command) throws Exception;
}
