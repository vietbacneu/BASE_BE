package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.mapper.NhapHangMapper;
import com.example.qlbhbe.repo.nhaphang.NhapHangRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhapHangServiceImpl extends AbstractService<NhapHang, Long> implements NhapHangService {

    private final NhapHangRepo nhapHangRepo;

    @Autowired
    public NhapHangServiceImpl(NhapHangRepo nhapHangRepo) {
        super(nhapHangRepo);
        this.nhapHangRepo = nhapHangRepo;
    }

    @Override
    public NhapHang update(long id, UpdateNhapHangRequest command) {
        Optional<NhapHang> opt = nhapHangRepo.findById(id);
        if (opt.isPresent()) {
            NhapHang nhapHang = opt.get();
            return NhapHangMapper.INSTANCE.update(command, nhapHang);
        }
        return null;
    }
}
