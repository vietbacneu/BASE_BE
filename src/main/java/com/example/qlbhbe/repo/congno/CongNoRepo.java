package com.example.qlbhbe.repo.congno;

import com.example.qlbhbe.entity.CongNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CongNoRepo extends JpaRepository<CongNo, Long> {

    @Modifying
    @Query("delete from CongNo n where n.id = :id")
    void deleteCongNo(@Param("id") Long id);
}
