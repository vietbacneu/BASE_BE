package com.example.qlbhbe.service.trocap;

import com.example.qlbhbe.controller.request.searchparams.TroCapSearchParams;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.repo.trocap.TroCapRepo;
import com.example.qlbhbe.service.BaseSearchServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TroCapSearchServiceImpl extends BaseSearchServiceImpl<TroCapDTO, TroCapSearchParams> implements TroCapSearchService {

    public TroCapSearchServiceImpl(TroCapRepo troCapRepo) {
        super(troCapRepo);
    }

}
