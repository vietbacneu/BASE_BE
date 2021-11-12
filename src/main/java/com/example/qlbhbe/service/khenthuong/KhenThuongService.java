package com.example.qlbhbe.service.khenthuong;

import com.example.qlbhbe.controller.request.UpdateKhenThuongRequest;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface KhenThuongService extends BaseService<KhenThuong, Long> {

    KhenThuong update(long id, KhenThuongDTO command);

    Page<KhenThuongDTO> search(@RequestBody(required = false) KhenThuongDTO command, @PageableDefault Pageable pageable) throws Exception;

}
