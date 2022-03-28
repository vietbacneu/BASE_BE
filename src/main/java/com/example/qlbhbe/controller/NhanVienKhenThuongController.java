package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.NhanVienKhenThuongMapper;
import com.example.qlbhbe.service.nhanvienkhenthuong.NhanVienKhenThuongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhanVienKhenThuongs")
@Validated
public class NhanVienKhenThuongController {

    private final NhanVienKhenThuongService nhanVienKhenThuongService;

    @PersistenceContext
    EntityManager entityManager;

    public NhanVienKhenThuongController(NhanVienKhenThuongService nhanVienKhenThuongService) {
        this.nhanVienKhenThuongService = nhanVienKhenThuongService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienKhenThuongDTO command) {
        NhanVienKhenThuong nhanVienKhenThuong = NhanVienKhenThuongMapper.INSTANCE.create(command);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(command.getNhanVienId());
        nhanVienKhenThuong.setNhanVien(nhanVien);
        KhenThuong kt = new KhenThuong();
        kt.setId(command.getIdKhenThuong());
        nhanVienKhenThuong.setKhenThuong(kt);
        nhanVienKhenThuongService.save(nhanVienKhenThuong);
        return new CreatedIdResponse(nhanVienKhenThuong.getId());
    }

    @PostMapping("/search")
    public Page<NhanVienKhenThuongDTO> search(@RequestBody(required = false) NhanVienKhenThuongDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhanVienKhenThuongService.search(command, pageable);
    }

    @PutMapping("{id}")
    public void update(@RequestBody NhanVienKhenThuongDTO command) {
        nhanVienKhenThuongService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienKhenThuongService.deleteById(id);
    }

}
