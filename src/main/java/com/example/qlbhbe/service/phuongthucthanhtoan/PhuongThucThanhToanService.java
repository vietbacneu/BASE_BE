package com.example.qlbhbe.service.phuongthucthanhtoan;

import com.example.qlbhbe.dto.PhuongThucThanhToanDTO;
import com.example.qlbhbe.entity.PhuongThucThanhToan;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PhuongThucThanhToanService extends BaseService<PhuongThucThanhToan, Long> {

    PhuongThucThanhToan update(long id, PhuongThucThanhToanDTO command);

    Page<PhuongThucThanhToanDTO> searchAllPPTT(PhuongThucThanhToanDTO danhMucDTO, Pageable pageable) throws Exception;
}
