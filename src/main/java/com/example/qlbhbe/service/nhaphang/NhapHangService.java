package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface NhapHangService extends BaseService<NhapHang, Long> {

    MessageDTO update(long id, NhapHangDTO command);

    Page<NhapHangDTO> search(NhapHangDTO command, Pageable pageable) throws Exception;

    MessageDTO save(NhapHangDTO nhapHangDTO);

    List<NhapHangDTO> getNhapHangMax(NhapHangDTO sanPhamDTO) throws Exception;

    Map<String, String> exportNhapMax(NhapHangDTO sanPhamDTO) throws Exception;
}
