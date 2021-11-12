package com.example.qlbhbe.service.khenthuong;

import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.mapper.KhenThuongMapper;
import com.example.qlbhbe.repo.khenthuong.KhenThuongRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class KhenThuongServiceImpl extends AbstractService<KhenThuong, Long> implements KhenThuongService {

    private final KhenThuongRepo khenThuongRepo;

    @Autowired
    public KhenThuongServiceImpl(KhenThuongRepo khenThuongRepo) {
        super(khenThuongRepo);
        this.khenThuongRepo = khenThuongRepo;
    }

    @Override
    public KhenThuong update(long id, KhenThuongDTO command) {
        Optional<KhenThuong> opt = khenThuongRepo.findById(id);
        if (opt.isPresent()) {
            KhenThuong khenThuong = opt.get();
            return KhenThuongMapper.INSTANCE.update(command, khenThuong);
        }
        return null;
    }
}
