package com.example.qlbhbe.repo.nhanvien;

import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhapHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepo  extends JpaRepository<NhanVien, Long> {
}
