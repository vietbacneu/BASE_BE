package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.entity.BanHang;
import com.example.qlbhbe.mapper.BanHangMapper;
import com.example.qlbhbe.service.banhang.BanHangService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

;
;

@RestController
@RequestMapping(Constants.API + "/banHangs")
@Validated
public class BanHangController {

    private final BanHangService banHangService;

    public BanHangController(BanHangService banHangService) {
        this.banHangService = banHangService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody BanHangDTO command) {
        BanHang banHang = BanHangMapper.INSTANCE.create(command);
        banHangService.save(banHang);
        return new CreatedIdResponse(banHang.getId());
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody BanHangDTO command) {
        banHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        banHangService.deleteById(id);
    }

}
