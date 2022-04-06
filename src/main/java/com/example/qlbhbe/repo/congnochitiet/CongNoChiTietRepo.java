package com.example.qlbhbe.repo.congnochitiet;

import com.example.qlbhbe.entity.CongNoChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CongNoChiTietRepo extends JpaRepository<CongNoChiTiet, Long> {

    @Modifying
    @Query("delete from CongNoChiTiet n where n.idCongNo.id = :id")
    void deleteCongNo(@Param("id") Long id);
    @Query(" from CongNoChiTiet n where n.idCongNo.id = :id")
    List<CongNoChiTiet> searchByCongNoId(Long id);
}
