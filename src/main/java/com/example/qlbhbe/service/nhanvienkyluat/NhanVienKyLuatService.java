package com.example.qlbhbe.service.nhanvienkyluat;

import com.example.qlbhbe.controller.request.UpdateNhanVienKyLuatRequest;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import com.example.qlbhbe.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;


public interface NhanVienKyLuatService extends BaseService<NhanVienKyLuat, Long> {

    NhanVienKyLuat update(long id, NhanVienKyLuatDTO command);

     Page<NhanVienKyLuatDTO> search(NhanVienKyLuatDTO command, Pageable pageable) throws Exception;

}
