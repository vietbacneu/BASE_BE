package com.example.qlbhbe.service.nhaphangchitiet;

import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.mapper.NhapHangChiTietMapper;
import com.example.qlbhbe.repo.nhaphangchitiet.NhapHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhapHangChiTietServiceImpl extends AbstractService<NhapHangChiTiet, Long> implements NhapHangChiTietService {

    private final NhapHangChiTietRepo nhapHangChiTietRepo;

    @Autowired
    public NhapHangChiTietServiceImpl(NhapHangChiTietRepo nhapHangChiTietRepo) {
        super(nhapHangChiTietRepo);
        this.nhapHangChiTietRepo = nhapHangChiTietRepo;
    }

    @Override
    public NhapHangChiTiet update(long id, UpdateNhapHangChiTietRequest command) {
        Optional<NhapHangChiTiet> opt = nhapHangChiTietRepo.findById(id);
        if (opt.isPresent()) {
            NhapHangChiTiet nhapHangChiTiet = opt.get();
            return NhapHangChiTietMapper.INSTANCE.update(command, nhapHangChiTiet);
        }
        return null;
    }
}
