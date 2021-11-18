package com.example.qlbhbe.service.kyluat;

import com.example.qlbhbe.controller.request.UpdateKyLuatRequest;
import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface KyLuatService extends BaseService<KyLuat, Long> {

    KyLuat update(long id, KyLuatDTO command);

    public Page<KyLuatDTO> search(@RequestBody(required = false) KyLuatDTO command, @PageableDefault Pageable pageable) throws Exception;

}
