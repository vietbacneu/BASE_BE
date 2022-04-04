package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.controller.request.UpdateCongNoChiTietRequest;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.service.BaseService;


public interface CongNoChiTietService extends BaseService<CongNoChiTiet, Long> {

    CongNoChiTiet update(long id, CongNoChiTietDTO command);

}
