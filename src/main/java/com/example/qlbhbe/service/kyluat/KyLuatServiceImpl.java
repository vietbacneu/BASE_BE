package com.example.qlbhbe.service.kyluat;

import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.mapper.KyLuatMapper;
import com.example.qlbhbe.repo.kyluat.KyLuatRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class KyLuatServiceImpl extends AbstractService<KyLuat, Long> implements KyLuatService {

    private final KyLuatRepo kyLuatRepo;

    @Autowired
    public KyLuatServiceImpl(KyLuatRepo kyLuatRepo) {
        super(kyLuatRepo);
        this.kyLuatRepo = kyLuatRepo;
    }

    @Override
    public KyLuat update(long id, KyLuatDTO command) {
        Optional<KyLuat> opt = kyLuatRepo.findById(id);
        if (opt.isPresent()) {
            KyLuat kyLuat = opt.get();
            return KyLuatMapper.INSTANCE.update(command, kyLuat);
        }
        return null;
    }
}
