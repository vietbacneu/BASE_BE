package com.example.qlbhbe.service.banhang;

import com.example.qlbhbe.controller.request.UpdateBanHangRequest;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.mapper.BanHangMapper;
import com.example.qlbhbe.repo.banhang.BanHangRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class BanHangServiceImpl extends AbstractService<BanHang, Long> implements BanHangService {

    private final BanHangRepo banHangRepo;

    @Autowired
    public BanHangServiceImpl(BanHangRepo banHangRepo) {
        super(banHangRepo);
        this.banHangRepo = banHangRepo;
    }

    @Override
    public BanHang update(long id, BanHangDTO command) {
        Optional<BanHang> opt = banHangRepo.findById(id);
        if (opt.isPresent()) {
            BanHang banHang = opt.get();
            return BanHangMapper.INSTANCE.update(command, banHang);
        }
        return null;
    }
}
