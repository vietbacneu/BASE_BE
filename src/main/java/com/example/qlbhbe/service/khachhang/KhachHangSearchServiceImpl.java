package com.example.qlbhbe.service.khachhang;

import com.example.qlbhbe.controller.request.searchparams.KhachHangSearchParams;
import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.repo.khachhang.KhachHangRepo;
import com.example.qlbhbe.service.BaseSearchServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class KhachHangSearchServiceImpl extends BaseSearchServiceImpl<KhachHangDTO, KhachHangSearchParams> implements KhachHangSearchService {

    public KhachHangSearchServiceImpl(KhachHangRepo khachHangRepo) {
        super(khachHangRepo);
    }

}
