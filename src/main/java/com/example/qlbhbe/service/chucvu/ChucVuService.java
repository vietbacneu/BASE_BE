package com.example.qlbhbe.service.chucvu;

import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChucVuService extends BaseService<ChucVu, Long> {

    ChucVu update(long id, ChucVuDTO command);

    Page<ChucVuDTO> search(ChucVuDTO command, Pageable pageable) throws Exception;

}
