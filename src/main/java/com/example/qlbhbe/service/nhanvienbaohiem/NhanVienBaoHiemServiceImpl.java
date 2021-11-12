package com.example.qlbhbe.service.nhanvienbaohiem;

import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import com.example.qlbhbe.mapper.NhanVienBaoHiemMapper;
import com.example.qlbhbe.repo.nhanvienbaohiem.NhanVienBaoHiemRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhanVienBaoHiemServiceImpl extends AbstractService<NhanVienBaoHiem, Long> implements NhanVienBaoHiemService {

    private final NhanVienBaoHiemRepo nhanVienBaoHiemRepo;

    @Autowired
    public NhanVienBaoHiemServiceImpl(NhanVienBaoHiemRepo nhanVienBaoHiemRepo) {
        super(nhanVienBaoHiemRepo);
        this.nhanVienBaoHiemRepo = nhanVienBaoHiemRepo;
    }

    @Override
    public NhanVienBaoHiem update(long id, NhanVienBaoHiemDTO command) {
        Optional<NhanVienBaoHiem> opt = nhanVienBaoHiemRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienBaoHiem nhanVienBaoHiem = opt.get();
            return NhanVienBaoHiemMapper.INSTANCE.update(command, nhanVienBaoHiem);
        }
        return null;
    }
}
