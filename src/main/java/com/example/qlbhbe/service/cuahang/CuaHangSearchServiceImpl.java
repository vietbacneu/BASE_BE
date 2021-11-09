package com.example.qlbhbe.service.cuahang;

import com.example.qlbhbe.controller.request.searchparams.CuaHangSearchParams;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.repo.cuahang.CuaHangRepo;
import com.example.qlbhbe.service.BaseSearchServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CuaHangSearchServiceImpl extends BaseSearchServiceImpl<CuaHangDTO, CuaHangSearchParams> implements CuaHangSearchService {

    public CuaHangSearchServiceImpl(CuaHangRepo cuaHangRepo) {
        super(cuaHangRepo);
    }

}
