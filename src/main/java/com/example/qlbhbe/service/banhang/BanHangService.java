package com.example.qlbhbe.service.banhang;

import com.example.qlbhbe.controller.request.UpdateBanHangRequest;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface BanHangService extends BaseService<BanHang, Long> {

    BanHang update(long id, BanHangDTO command);
    MessageDTO save(BanHangDTO nhapHangDTO);
    public Page<BanHangDTO> search(@RequestBody BanHangDTO command, @PageableDefault Pageable pageable) throws Exception;
}
