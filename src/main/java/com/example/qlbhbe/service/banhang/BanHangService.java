package com.example.qlbhbe.service.banhang;

import com.example.qlbhbe.controller.request.UpdateBanHangRequest;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.service.BaseService;


public interface BanHangService extends BaseService<BanHang, Long> {

    BanHang update(long id, BanHangDTO command);

}
