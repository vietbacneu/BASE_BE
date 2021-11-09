package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface XuatHangService extends BaseService<XuatHang, Long> {

    XuatHang update(long id, UpdateXuatHangRequest command);

    Page<XuatHangDTO> search( XuatHangDTO command, Pageable pageable) throws Exception;

}
