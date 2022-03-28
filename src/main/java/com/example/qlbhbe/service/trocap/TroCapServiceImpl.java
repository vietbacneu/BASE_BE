package com.example.qlbhbe.service.trocap;

import com.example.qlbhbe.controller.request.UpdateTroCapRequest;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.TroCapMapper;
import com.example.qlbhbe.repo.trocap.TroCapRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class TroCapServiceImpl extends AbstractService<TroCap, Long> implements TroCapService {

    private final TroCapRepo troCapRepo;

    @Autowired
    public TroCapServiceImpl(TroCapRepo troCapRepo) {
        super(troCapRepo);
        this.troCapRepo = troCapRepo;
    }

    @Override
    public TroCap update(long id, TroCapDTO command) {
        Optional<TroCap> opt = troCapRepo.findById(id);
        if (opt.isPresent()) {
            TroCap troCap = opt.get();
            return TroCapMapper.INSTANCE.update(command, troCap);
        }
        return null;
    }
}
