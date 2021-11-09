package com.example.qlbhbe.repo.nhaphangchitiet;

import com.example.qlbhbe.entity.NhapHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NhapHangChiTietRepo extends JpaRepository<NhapHangChiTiet, Long> {
    @Modifying
    @Query("delete from NhapHangChiTiet n where n.nhapHang.id = :id")
    void deleteSanPham(@Param("id") Long id);
}
