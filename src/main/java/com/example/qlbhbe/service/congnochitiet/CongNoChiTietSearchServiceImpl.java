package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.controller.request.searchparams.CongNoChiTietSearchParams;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
import com.example.qlbhbe.service.BaseSearchServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CongNoChiTietSearchServiceImpl extends BaseSearchServiceImpl<CongNoChiTietDTO, CongNoChiTietSearchParams> implements CongNoChiTietSearchService {

    public CongNoChiTietSearchServiceImpl(CongNoChiTietRepo congNoChiTietRepo) {
        super(congNoChiTietRepo);
    }

}
