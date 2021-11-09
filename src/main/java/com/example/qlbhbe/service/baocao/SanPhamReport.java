package com.example.qlbhbe.service.baocao;

import com.example.qlbhbe.dto.SanPhamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamReport {
    List<SanPhamDTO> getSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception;

    List<SanPhamDTO> getSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception;

    List<SanPhamDTO> getSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception;

}
