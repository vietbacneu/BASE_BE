package com.example.qlbhbe.service.danhmuc;

import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.mapper.DanhMucMapper;
import com.example.qlbhbe.repo.danhmuc.DanhMucRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class DanhMucServiceImpl extends AbstractService<DanhMuc, Long> implements DanhMucService {

    private final DanhMucRepo danhMucRepo;

    @Autowired
    public DanhMucServiceImpl(DanhMucRepo danhMucRepo) {
        super(danhMucRepo);
        this.danhMucRepo = danhMucRepo;
    }

    @Override
    public DanhMuc update(long id, UpdateDanhMucRequest command) {
        Optional<DanhMuc> opt = danhMucRepo.findById(id);
        if (opt.isPresent()) {
            DanhMuc danhMuc = opt.get();
            return DanhMucMapper.INSTANCE.update(command, danhMuc);
        }
        return null;
    }
}
