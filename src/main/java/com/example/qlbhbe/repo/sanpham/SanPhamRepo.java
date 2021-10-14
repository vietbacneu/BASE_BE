package com.example.qlbhbe.repo.sanpham;

import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepo extends JpaRepository<SanPham, Long> {
}
