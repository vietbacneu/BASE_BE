package com.example.qlbhbe.service.khachhang;

import com.example.qlbhbe.dto.KhachHangDetailsDTO;
import com.example.qlbhbe.repo.khachhang.KhachHangRepo;
import com.example.qlbhbe.service.BaseDetailsServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class KhachHangDetailsServiceImpl extends BaseDetailsServiceImpl<KhachHangDetailsDTO, Long> implements KhachHangDetailsService{

    public KhachHangDetailsServiceImpl(KhachHangRepo khachHangRepo) {
        super(khachHangRepo);
    }

}
