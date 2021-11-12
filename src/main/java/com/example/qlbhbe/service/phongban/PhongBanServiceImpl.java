package com.example.qlbhbe.service.phongban;

import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.mapper.PhongBanMapper;
import com.example.qlbhbe.repo.phongban.PhongBanRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class PhongBanServiceImpl extends AbstractService<PhongBan, Long> implements PhongBanService {

    private final PhongBanRepo phongBanRepo;

    @Autowired
    public PhongBanServiceImpl(PhongBanRepo phongBanRepo) {
        super(phongBanRepo);
        this.phongBanRepo = phongBanRepo;
    }

    @Override
    public PhongBan update(long id, PhongBanDTO command) {
        Optional<PhongBan> opt = phongBanRepo.findById(id);
        if (opt.isPresent()) {
            PhongBan phongBan = opt.get();
            return PhongBanMapper.INSTANCE.update(command, phongBan);
        }
        return null;
    }
}
