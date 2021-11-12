package com.example.qlbhbe.service.kyluat;

import com.example.qlbhbe.controller.request.UpdateKyLuatRequest;
import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.service.BaseService;


public interface KyLuatService extends BaseService<KyLuat, Long> {

    KyLuat update(long id, KyLuatDTO command);

}
