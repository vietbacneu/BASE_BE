package com.example.qlbhbe.repo.banhangchitiet;

import com.example.qlbhbe.entity.BanHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BanHangChiTietRepo extends JpaRepository<BanHangChiTiet, Long> {
    @Modifying
    @Query("delete from BanHangChiTiet n where n.banHang.id = :id")
    void deleteSanPham(@Param("id") Long id);

    @Query("from BanHangChiTiet n where n.banHang.id = :id")
    List<BanHangChiTiet> getList(@Param("id") Long id);
}
