package com.example.qlbhbe.service;


import com.example.qlbhbe.repo.BaseDetailsRepo;

import java.io.Serializable;
import java.util.Optional;

public abstract class BaseDetailsServiceImpl<DT, I extends Serializable> implements BaseDetailsService<DT, I> {

    private final BaseDetailsRepo<DT, I> baseDetailsRepo;

    public BaseDetailsServiceImpl(BaseDetailsRepo<DT, I> baseDetailsRepo) {
        this.baseDetailsRepo = baseDetailsRepo;
    }

    @Override
    public Optional<DT> findDetailsDTOById(I id) {
        return baseDetailsRepo.findDetailsDTOById(id);
    }

}
