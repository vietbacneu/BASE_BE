package com.example.qlbhbe.service.phongban;

import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface PhongBanService extends BaseService<PhongBan, Long> {

    PhongBan update(long id, PhongBanDTO command);

    public Page<PhongBanDTO> search(@RequestBody(required = false) PhongBanDTO command, @PageableDefault Pageable pageable) throws Exception;
}
