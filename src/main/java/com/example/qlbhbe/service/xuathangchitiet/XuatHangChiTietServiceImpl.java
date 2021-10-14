package com.example.qlbhbe.service.xuathangchitiet;

import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.mapper.XuatHangChiTietMapper;
import com.example.qlbhbe.repo.xuathangchitiet.XuatHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class XuatHangChiTietServiceImpl extends AbstractService<XuatHangChiTiet, Long> implements XuatHangChiTietService {

    private final XuatHangChiTietRepo xuatHangChiTietRepo;

    @Autowired
    public XuatHangChiTietServiceImpl(XuatHangChiTietRepo xuatHangChiTietRepo) {
        super(xuatHangChiTietRepo);
        this.xuatHangChiTietRepo = xuatHangChiTietRepo;
    }

    @Override
    public XuatHangChiTiet update(long id, UpdateXuatHangChiTietRequest command) {
        Optional<XuatHangChiTiet> opt = xuatHangChiTietRepo.findById(id);
        if (opt.isPresent()) {
            XuatHangChiTiet xuatHangChiTiet = opt.get();
            return XuatHangChiTietMapper.INSTANCE.update(command, xuatHangChiTiet);
        }
        return null;
    }
}
