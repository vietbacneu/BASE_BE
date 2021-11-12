package com.example.qlbhbe.service.khenthuong;

import com.example.qlbhbe.controller.request.UpdateKhenThuongRequest;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.service.BaseService;


public interface KhenThuongService extends BaseService<KhenThuong, Long> {

    KhenThuong update(long id, KhenThuongDTO command);

}
