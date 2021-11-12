package com.example.qlbhbe.service.chamcong;

import com.example.qlbhbe.controller.request.UpdateChamCongRequest;
import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.service.BaseService;


public interface ChamCongService extends BaseService<ChamCong, Long> {

    ChamCong update(long id, ChamCongDTO command);

}
