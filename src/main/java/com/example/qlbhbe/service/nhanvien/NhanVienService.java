package com.example.qlbhbe.service.nhanvien;

import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NhanVienService extends BaseService<NhanVien, Long> {

    NhanVien update(long id, NhanVienDTO command);

    Page<NhanVienDTO> search(NhanVienDTO params, Pageable pageable) throws Exception;

}
