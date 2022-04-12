package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.entity.BanHangChiTiet;
import com.example.qlbhbe.mapper.BanHangChiTietMapper;
import com.example.qlbhbe.service.banhangchitiet.BanHangChiTietService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

;
;

@RestController
@RequestMapping(Constants.API + "/banHangChiTiets")
@Validated
public class BanHangChiTietController {

    private final BanHangChiTietService banHangChiTietService;

    public BanHangChiTietController(BanHangChiTietService banHangChiTietService) {
        this.banHangChiTietService = banHangChiTietService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody BanHangChiTietDTO command) {
        BanHangChiTiet banHangChiTiet = BanHangChiTietMapper.INSTANCE.create(command);
        banHangChiTietService.save(banHangChiTiet);
        return new CreatedIdResponse(banHangChiTiet.getId());
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody BanHangChiTietDTO command) {
        banHangChiTietService.update(command.getId(), command);
    }

    @DeleteMapping("{id}") public void delete(@PathVariable(name = "id") long id) {
        banHangChiTietService.deleteById(id);
    }

}
