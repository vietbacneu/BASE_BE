package com.example.qlbhbe.service.nhaphangchitiet;

import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface NhapHangChiTietService extends BaseService<NhapHangChiTiet, Long> {

    NhapHangChiTiet update(long id, UpdateNhapHangChiTietRequest command);

    List<NhapHangChiTietDTO> search(NhapHangChiTietDTO command) throws Exception;
}
