package com.example.qlbhbe.service.baocao;

import com.example.qlbhbe.dto.SanPhamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SanPhamReport {
    List<SanPhamDTO> getSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception;

    List<SanPhamDTO> getSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception;

    List<SanPhamDTO> getSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception;

    Map<String,String> exportSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception;

    public Map<String,String> exportSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception;

    public Map<String,String> exportSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception;

}
