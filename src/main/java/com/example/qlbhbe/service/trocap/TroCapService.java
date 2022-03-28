package com.example.qlbhbe.service.trocap;

import com.example.qlbhbe.controller.request.UpdateTroCapRequest;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.service.BaseService;


public interface TroCapService extends BaseService<TroCap, Long> {

    TroCap update(long id, TroCapDTO command);

}
