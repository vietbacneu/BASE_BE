package com.example.qlbhbe.service;

import com.example.qlbhbe.repo.BaseSearchRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class BaseSearchServiceImpl<LI, SP> implements BaseSearchService<LI, SP> {

    private final BaseSearchRepo<LI, SP> baseSearchRepo;

    public BaseSearchServiceImpl(BaseSearchRepo<LI, SP> baseSearchRepo) {
        this.baseSearchRepo = baseSearchRepo;
    }

    @Override
    public Page<LI> search(SP params, Pageable pageable) {
        return baseSearchRepo.search(params, pageable);
    }

}
