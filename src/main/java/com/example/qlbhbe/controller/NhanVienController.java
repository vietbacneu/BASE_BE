package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.NhanVienMapper;
import com.example.qlbhbe.service.nhanvien.NhanVienService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        nhanVienService.save(nhanVien);
        return new CreatedIdResponse(nhanVien.getId());
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
