package com.example.qlbhbe.repo.banhang;

import com.example.qlbhbe.controller.request.searchparams.BanHangSearchParams;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.repo.BaseSearchRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BanHangRepo extends JpaRepository<BanHang, Long> {

    @Modifying
    @Query("delete from BanHang n where n.id = :id")
    void deleteSanPham(@Param("id") Long id);
}
