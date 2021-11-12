package com.example.qlbhbe.service.chucvu;

import com.example.qlbhbe.controller.request.UpdateChucVuRequest;
import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.service.BaseService;


public interface ChucVuService extends BaseService<ChucVu, Long> {

    ChucVu update(long id, ChucVuDTO command);

}
