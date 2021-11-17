package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.mapper.NhanVienMapper;
import com.example.qlbhbe.service.nhanvien.NhanVienService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(Constants.API + "/nhanViens")
@Validated
public class NhanVienController {

    private final NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienDTO command) {
        NhanVien nhanVien = NhanVienMapper.INSTANCE.create(command);
        ChucVu chucVu = new ChucVu();
        chucVu.setId(command.getChucVuId());
        nhanVien.setChucVu(chucVu);
        PhongBan phongBan = new PhongBan();
        phongBan.setId(command.getPhongBanId());
        nhanVien.setPhongBan(phongBan);
        nhanVien.setSdt(command.getSdt());
        nhanVien.setNgayBatDau(command.getNgayBatDau());
        nhanVienService.save(nhanVien);
        return new CreatedIdResponse(nhanVien.getId());
    }

    @PostMapping("/search")
    public Page<NhanVienDTO> search(@RequestBody(required = false) NhanVienDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhanVienService.search(command, pageable);
    }

    @PostMapping("/exportNhanVien")
    public Map<String,String> exportNhanVien(@RequestBody(required = false) NhanVienDTO command) throws Exception {
        return nhanVienService.exportNhanVien(command);
    }

    @PutMapping("{id}")
    public void update(@RequestBody NhanVienDTO command) {
        nhanVienService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienService.deleteById(id);
    }

}
