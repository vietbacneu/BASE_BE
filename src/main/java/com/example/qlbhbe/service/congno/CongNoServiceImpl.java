package com.example.qlbhbe.service.congno;

import com.example.qlbhbe.controller.request.UpdateCongNoRequest;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import com.example.qlbhbe.mapper.CongNoMapper;
import com.example.qlbhbe.repo.congno.CongNoRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class CongNoServiceImpl extends AbstractService<CongNo, Long> implements CongNoService {

    private final CongNoRepo congNoRepo;

    @Autowired
    public CongNoServiceImpl(CongNoRepo congNoRepo) {
        super(congNoRepo);
        this.congNoRepo = congNoRepo;
    }

    @Override
    public CongNo update(long id, CongNoDTO command) {
        Optional<CongNo> opt = congNoRepo.findById(id);
        if (opt.isPresent()) {
            CongNo congNo = opt.get();
            return CongNoMapper.INSTANCE.update(command, congNo);
        }
        return null;
    }
}
