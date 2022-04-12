package com.example.qlbhbe.repo.banhang;

import com.example.qlbhbe.controller.request.searchparams.BanHangSearchParams;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.repo.BaseSearchRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanHangRepo extends JpaRepository<BanHang, Long> {
}
