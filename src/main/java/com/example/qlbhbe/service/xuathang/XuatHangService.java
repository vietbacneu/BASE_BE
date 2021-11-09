package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface XuatHangService extends BaseService<XuatHang, Long> {

    MessageDTO update(long id, XuatHangDTO command);

    Page<XuatHangDTO> search(XuatHangDTO command, Pageable pageable) throws Exception;

    MessageDTO save(XuatHangDTO xuatHangDTO);

}
