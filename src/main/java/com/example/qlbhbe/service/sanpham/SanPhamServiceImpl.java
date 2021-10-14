package com.example.qlbhbe.service.sanpham;

import com.example.qlbhbe.controller.request.UpdateSanPhamRequest;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.mapper.SanPhamMapper;
import com.example.qlbhbe.repo.sanpham.SanPhamRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class SanPhamServiceImpl extends AbstractService<SanPham, Long> implements SanPhamService {

    private final SanPhamRepo sanPhamRepo;

    @Autowired
    public SanPhamServiceImpl(SanPhamRepo sanPhamRepo) {
        super(sanPhamRepo);
        this.sanPhamRepo = sanPhamRepo;
    }

    @Override
    public SanPham update(long id, UpdateSanPhamRequest command) {
        Optional<SanPham> opt = sanPhamRepo.findById(id);
        if (opt.isPresent()) {
            SanPham sanPham = opt.get();
            return SanPhamMapper.INSTANCE.update(command, sanPham);
        }
        return null;
    }
}
