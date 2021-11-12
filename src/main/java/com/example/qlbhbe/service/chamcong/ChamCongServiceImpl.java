package com.example.qlbhbe.service.chamcong;

import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.mapper.ChamCongMapper;
import com.example.qlbhbe.repo.chamcong.ChamCongRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ChamCongServiceImpl extends AbstractService<ChamCong, Long> implements ChamCongService {

    private final ChamCongRepo chamCongRepo;

    @Autowired
    public ChamCongServiceImpl(ChamCongRepo chamCongRepo) {
        super(chamCongRepo);
        this.chamCongRepo = chamCongRepo;
    }

    @Override
    public ChamCong update(long id, ChamCongDTO command) {
        Optional<ChamCong> opt = chamCongRepo.findById(id);
        if (opt.isPresent()) {
            ChamCong chamCong = opt.get();
            return ChamCongMapper.INSTANCE.update(command, chamCong);
        }
        return null;
    }
}
