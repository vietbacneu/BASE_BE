package com.example.qlbhbe.service.banhangchitiet;

import com.example.qlbhbe.controller.request.UpdateBanHangChiTietRequest;
import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.entity.BanHangChiTiet;
import com.example.qlbhbe.service.BaseService;


public interface BanHangChiTietService extends BaseService<BanHangChiTiet, Long> {

    BanHangChiTiet update(long id, BanHangChiTietDTO command);

}
