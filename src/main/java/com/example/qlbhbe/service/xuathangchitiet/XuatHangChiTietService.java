package com.example.qlbhbe.service.xuathangchitiet;

import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface XuatHangChiTietService extends BaseService<XuatHangChiTiet, Long> {

    XuatHangChiTiet update(long id, UpdateXuatHangChiTietRequest command);

    List<XuatHangChiTietDTO> search(XuatHangChiTietDTO command) throws Exception;

}
