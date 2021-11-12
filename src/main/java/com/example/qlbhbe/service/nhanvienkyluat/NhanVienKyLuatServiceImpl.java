package com.example.qlbhbe.service.nhanvienkyluat;

import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import com.example.qlbhbe.mapper.NhanVienKyLuatMapper;
import com.example.qlbhbe.repo.nhanvienkyluat.NhanVienKyLuatRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhanVienKyLuatServiceImpl extends AbstractService<NhanVienKyLuat, Long> implements NhanVienKyLuatService {

    private final NhanVienKyLuatRepo nhanVienKyLuatRepo;

    @Autowired
    public NhanVienKyLuatServiceImpl(NhanVienKyLuatRepo nhanVienKyLuatRepo) {
        super(nhanVienKyLuatRepo);
        this.nhanVienKyLuatRepo = nhanVienKyLuatRepo;
    }

    @Override
    public NhanVienKyLuat update(long id, NhanVienKyLuatDTO command) {
        Optional<NhanVienKyLuat> opt = nhanVienKyLuatRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienKyLuat nhanVienKyLuat = opt.get();
            return NhanVienKyLuatMapper.INSTANCE.update(command, nhanVienKyLuat);
        }
        return null;
    }
}
