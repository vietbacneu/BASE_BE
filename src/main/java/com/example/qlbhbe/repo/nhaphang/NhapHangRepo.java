package com.example.qlbhbe.repo.nhaphang;

import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.entity.NhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NhapHangRepo extends JpaRepository<NhapHang, Long> {
    @Modifying
    @Query("delete from NhapHang n where n.id = :id")
    void deleteSanPham(@Param("id") Long id);
}
