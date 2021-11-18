package com.example.qlbhbe.service.nhanvienkhenthuong;

import com.example.qlbhbe.controller.request.UpdateNhanVienKhenThuongRequest;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface NhanVienKhenThuongService extends BaseService<NhanVienKhenThuong, Long> {

    NhanVienKhenThuong update(long id, NhanVienKhenThuongDTO command);

    Page<NhanVienKhenThuongDTO> search( NhanVienKhenThuongDTO command, Pageable pageable) throws Exception;
}
