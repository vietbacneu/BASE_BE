package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import com.example.qlbhbe.mapper.NhanVienKyLuatMapper;
import com.example.qlbhbe.service.nhanvienkyluat.NhanVienKyLuatService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhanVienKyLuats")
@Validated
public class NhanVienKyLuatController {

    private final NhanVienKyLuatService nhanVienKyLuatService;

    public NhanVienKyLuatController(NhanVienKyLuatService nhanVienKyLuatService) {
        this.nhanVienKyLuatService = nhanVienKyLuatService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienKyLuatDTO command) {
        NhanVienKyLuat nhanVienKyLuat = NhanVienKyLuatMapper.INSTANCE.create(command);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(command.getIdNhanVien());
        nhanVienKyLuat.setNhanVien(nhanVien);
        KyLuat kt = new KyLuat();
        kt.setId(command.getIdKyLuat());
        nhanVienKyLuat.setKyLuat(kt);
        nhanVienKyLuatService.save(nhanVienKyLuat);
        return new CreatedIdResponse(nhanVienKyLuat.getId());
    }

    @PostMapping("/search")
    public Page<NhanVienKyLuatDTO> search(@RequestBody(required = false) NhanVienKyLuatDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhanVienKyLuatService.search(command, pageable);
    }

    @PutMapping("/update")
    public void update(@RequestBody NhanVienKyLuatDTO command) {
        nhanVienKyLuatService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienKyLuatService.deleteById(id);
    }

}
