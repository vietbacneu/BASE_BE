package com.example.qlbhbe.service.trocap;

import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface TroCapService extends BaseService<TroCap, Long> {

    TroCap update(long id, TroCapDTO command);

    public Page<TroCapDTO> search(@RequestBody(required = false) TroCapDTO command, @PageableDefault Pageable pageable) throws Exception;


}
