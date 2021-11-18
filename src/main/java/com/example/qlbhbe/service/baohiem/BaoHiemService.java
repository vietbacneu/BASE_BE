package com.example.qlbhbe.service.baohiem;

import com.example.qlbhbe.dto.BaoHiemDTO;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.BaoHiem;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BaoHiemService extends BaseService<BaoHiem, Long> {

    BaoHiem update(long id, BaoHiemDTO command);

    Page<BaoHiemDTO> search(BaoHiemDTO command, Pageable pageable) throws Exception;
}
