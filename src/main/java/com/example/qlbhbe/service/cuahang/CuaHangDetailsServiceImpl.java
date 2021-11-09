package com.example.qlbhbe.service.cuahang;

import com.example.qlbhbe.dto.CuaHangDetailsDTO;
import com.example.qlbhbe.repo.cuahang.CuaHangRepo;
import com.example.qlbhbe.service.BaseDetailsServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CuaHangDetailsServiceImpl extends BaseDetailsServiceImpl<CuaHangDetailsDTO, Long> implements CuaHangDetailsService{

    public CuaHangDetailsServiceImpl(CuaHangRepo cuaHangRepo) {
        super(cuaHangRepo);
    }

}
