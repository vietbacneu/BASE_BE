package com.example.qlbhbe.service.nhanvientrocap;

import com.example.qlbhbe.controller.request.UpdateNhanVienTroCapRequest;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.mapper.NhanVienTroCapMapper;
import com.example.qlbhbe.repo.trocap.NhanVienTroCapRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhanVienTroCapServiceImpl extends AbstractService<NhanVienTroCap, Long> implements NhanVienTroCapService {

    private final NhanVienTroCapRepo nhanVienTroCapRepo;

    @Autowired
    public NhanVienTroCapServiceImpl(NhanVienTroCapRepo nhanVienTroCapRepo) {
        super(nhanVienTroCapRepo);
        this.nhanVienTroCapRepo = nhanVienTroCapRepo;
    }

    @Override
    public NhanVienTroCap update(long id, UpdateNhanVienTroCapRequest command) {
        Optional<NhanVienTroCap> opt = nhanVienTroCapRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienTroCap nhanVienTroCap = opt.get();
            return NhanVienTroCapMapper.INSTANCE.update(command, nhanVienTroCap);
        }
        return null;
    }
}
