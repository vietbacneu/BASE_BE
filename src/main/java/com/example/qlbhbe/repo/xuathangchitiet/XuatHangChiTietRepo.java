package com.example.qlbhbe.repo.xuathangchitiet;

import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuatHangChiTietRepo extends JpaRepository<XuatHangChiTiet, Long> {

    @Modifying
    @Query("delete from XuatHangChiTiet n where n.xuatHang.id = :id")
    void deleteSanPham(@Param("id") Long id);
}
