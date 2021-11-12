package com.example.qlbhbe.service.chucvu;

import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.mapper.ChucVuMapper;
import com.example.qlbhbe.repo.chucvu.ChucVuRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ChucVuServiceImpl extends AbstractService<ChucVu, Long> implements ChucVuService {

    private final ChucVuRepo chucVuRepo;

    @Autowired
    public ChucVuServiceImpl(ChucVuRepo chucVuRepo) {
        super(chucVuRepo);
        this.chucVuRepo = chucVuRepo;
    }

    @Override
    public ChucVu update(long id, ChucVuDTO command) {
        Optional<ChucVu> opt = chucVuRepo.findById(id);
        if (opt.isPresent()) {
            ChucVu chucVu = opt.get();
            return ChucVuMapper.INSTANCE.update(command, chucVu);
        }
        return null;
    }
}
