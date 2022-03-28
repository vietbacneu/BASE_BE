package com.example.qlbhbe.repo.trocap;

import com.example.qlbhbe.controller.request.searchparams.TroCapSearchParams;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.repo.BaseSearchRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TroCapRepo extends JpaRepository<TroCap, Long> {
}
