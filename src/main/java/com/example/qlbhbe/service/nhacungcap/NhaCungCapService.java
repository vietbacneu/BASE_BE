package com.example.qlbhbe.service.nhacungcap;

import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.NhaCungCap;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NhaCungCapService extends BaseService<NhaCungCap, Long> {

    NhaCungCap update(long id, UpdateNhaCungCapRequest command);

    Page<NhaCungCapDTO> search(NhaCungCapDTO command, Pageable pageable) throws Exception;
}
