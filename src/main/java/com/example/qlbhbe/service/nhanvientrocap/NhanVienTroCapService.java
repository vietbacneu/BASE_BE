package com.example.qlbhbe.service.nhanvientrocap;

import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;


public interface NhanVienTroCapService extends BaseService<NhanVienTroCap, Long> {

    NhanVienTroCap update(Long id, NhanVienTroCapDTO command) throws ParseException;

    public Page<NhanVienTroCapDTO> search(@RequestBody(required = false) NhanVienTroCapDTO command, @PageableDefault Pageable pageable) throws Exception;

}
