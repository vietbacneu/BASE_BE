package com.example.qlbhbe.service.nhacungcap;

import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.entity.NhaCungCap;
import com.example.qlbhbe.mapper.NhaCungCapMapper;
import com.example.qlbhbe.repo.nhacungcap.NhaCungCapRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class NhaCungCapServiceImpl extends AbstractService<NhaCungCap, Long> implements NhaCungCapService {

    private final NhaCungCapRepo nhaCungCapRepo;

    @Autowired
    public NhaCungCapServiceImpl(NhaCungCapRepo nhaCungCapRepo) {
        super(nhaCungCapRepo);
        this.nhaCungCapRepo = nhaCungCapRepo;
    }

    @Override
    public NhaCungCap update(long id, UpdateNhaCungCapRequest command) {
        Optional<NhaCungCap> opt = nhaCungCapRepo.findById(id);
        if (opt.isPresent()) {
            NhaCungCap nhaCungCap = opt.get();
            return NhaCungCapMapper.INSTANCE.update(command, nhaCungCap);
        }
        return null;
    }
}
