package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import com.example.qlbhbe.mapper.NhanVienKhenThuongMapper;
import com.example.qlbhbe.service.nhanvienkhenthuong.NhanVienKhenThuongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhanVienKhenThuongs")
@Validated
public class NhanVienKhenThuongController {

    private final NhanVienKhenThuongService nhanVienKhenThuongService;

    public NhanVienKhenThuongController(NhanVienKhenThuongService nhanVienKhenThuongService) {
        this.nhanVienKhenThuongService = nhanVienKhenThuongService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienKhenThuongDTO command) {
        NhanVienKhenThuong nhanVienKhenThuong = NhanVienKhenThuongMapper.INSTANCE.create(command);
        nhanVienKhenThuongService.save(nhanVienKhenThuong);
        return new CreatedIdResponse(nhanVienKhenThuong.getId());
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
