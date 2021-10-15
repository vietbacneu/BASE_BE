package com.example.qlbhbe.service.danhmuc;

import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DanhMucService extends BaseService<DanhMuc, Long> {

    DanhMuc update(long id, UpdateDanhMucRequest command);

    Page<DanhMucDTO> searchAllDanhMuc(DanhMucDTO danhMucDTO, Pageable pageable) throws Exception;
}
