package com.example.qlbhbe.service.banhangchitiet;

import com.example.qlbhbe.controller.request.UpdateBanHangChiTietRequest;
import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.entity.BanHangChiTiet;
import com.example.qlbhbe.mapper.BanHangChiTietMapper;
import com.example.qlbhbe.repo.banhangchitiet.BanHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class BanHangChiTietServiceImpl extends AbstractService<BanHangChiTiet, Long> implements BanHangChiTietService {

    private final BanHangChiTietRepo banHangChiTietRepo;

    @Autowired
    public BanHangChiTietServiceImpl(BanHangChiTietRepo banHangChiTietRepo) {
        super(banHangChiTietRepo);
        this.banHangChiTietRepo = banHangChiTietRepo;
    }

    @Override
    public BanHangChiTiet update(long id, BanHangChiTietDTO command) {
        Optional<BanHangChiTiet> opt = banHangChiTietRepo.findById(id);
        if (opt.isPresent()) {
            BanHangChiTiet banHangChiTiet = opt.get();
            return BanHangChiTietMapper.INSTANCE.update(command, banHangChiTiet);
        }
        return null;
    }
}
