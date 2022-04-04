package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.controller.request.UpdateCongNoChiTietRequest;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.mapper.CongNoChiTietMapper;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class CongNoChiTietServiceImpl extends AbstractService<CongNoChiTiet, Long> implements CongNoChiTietService {

    private final CongNoChiTietRepo congNoChiTietRepo;

    @Autowired
    public CongNoChiTietServiceImpl(CongNoChiTietRepo congNoChiTietRepo) {
        super(congNoChiTietRepo);
        this.congNoChiTietRepo = congNoChiTietRepo;
    }

    @Override
    public CongNoChiTiet update(long id, CongNoChiTietDTO command) {
        Optional<CongNoChiTiet> opt = congNoChiTietRepo.findById(id);
        if (opt.isPresent()) {
            CongNoChiTiet congNoChiTiet = opt.get();
            return CongNoChiTietMapper.INSTANCE.update(command, congNoChiTiet);
        }
        return null;
    }
}
