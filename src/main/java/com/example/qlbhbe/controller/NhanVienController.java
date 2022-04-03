package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.NhanVienMapper;
import com.example.qlbhbe.service.nhanvien.NhanVienService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

;

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

    @PostMapping("/search")
    public Page<NhanVienDTO> list(@RequestBody NhanVienDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return nhanVienService.search(params, pageable);
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody NhanVienDTO command) {
        nhanVienService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienService.deleteById(id);
    }

}
