package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.entity.KhachHang;
import com.example.qlbhbe.mapper.KhachHangMapper;
import com.example.qlbhbe.service.khachhang.KhachHangService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/khachHangs")
@Validated
public class KhachHangController {

    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody KhachHangDTO command) {
        KhachHang khachHang = KhachHangMapper.INSTANCE.create(command);
        khachHangService.save(khachHang);
        return new CreatedIdResponse(khachHang.getId());
    }

    @PostMapping("/search")
    public Page<KhachHangDTO> list(@RequestBody KhachHangDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return khachHangService.search(params, pageable);
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody KhachHangDTO command) {
        khachHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        khachHangService.deleteById(id);
    }

}
