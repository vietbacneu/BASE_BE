package com.example.qlbhbe.service.phongban;

import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.service.BaseService;


public interface PhongBanService extends BaseService<PhongBan, Long> {

    PhongBan update(long id, PhongBanDTO command);

}
