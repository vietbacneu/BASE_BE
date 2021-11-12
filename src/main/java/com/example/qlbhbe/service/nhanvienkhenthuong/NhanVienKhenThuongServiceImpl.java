package com.example.qlbhbe.service.nhanvienkhenthuong;

import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import com.example.qlbhbe.mapper.NhanVienKhenThuongMapper;
import com.example.qlbhbe.repo.nhanvienkhenthuong.NhanVienKhenThuongRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhanVienKhenThuongServiceImpl extends AbstractService<NhanVienKhenThuong, Long> implements NhanVienKhenThuongService {

    private final NhanVienKhenThuongRepo nhanVienKhenThuongRepo;

    @Autowired
    public NhanVienKhenThuongServiceImpl(NhanVienKhenThuongRepo nhanVienKhenThuongRepo) {
        super(nhanVienKhenThuongRepo);
        this.nhanVienKhenThuongRepo = nhanVienKhenThuongRepo;
    }

    @Override
    public NhanVienKhenThuong update(long id, NhanVienKhenThuongDTO command) {
        Optional<NhanVienKhenThuong> opt = nhanVienKhenThuongRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienKhenThuong nhanVienKhenThuong = opt.get();
            return NhanVienKhenThuongMapper.INSTANCE.update(command, nhanVienKhenThuong);
        }
        return null;
    }
}
