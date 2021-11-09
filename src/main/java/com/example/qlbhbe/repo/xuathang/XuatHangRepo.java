package com.example.qlbhbe.repo.xuathang;

import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.entity.XuatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuatHangRepo extends JpaRepository<XuatHang, Long> {
    @Modifying
    @Query("delete from XuatHang n where n.id = :id")
    void deleteSanPham(@Param("id") Long id);
}
