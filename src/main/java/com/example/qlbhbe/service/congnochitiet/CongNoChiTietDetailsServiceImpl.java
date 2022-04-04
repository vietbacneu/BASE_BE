package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.dto.CongNoChiTietDetailsDTO;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
import com.example.qlbhbe.service.BaseDetailsServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CongNoChiTietDetailsServiceImpl extends BaseDetailsServiceImpl<CongNoChiTietDetailsDTO, Long> implements CongNoChiTietDetailsService{

    public CongNoChiTietDetailsServiceImpl(CongNoChiTietRepo congNoChiTietRepo) {
        super(congNoChiTietRepo);
    }

}
