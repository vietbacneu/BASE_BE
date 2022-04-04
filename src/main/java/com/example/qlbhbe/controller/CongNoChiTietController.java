package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.mapper.CongNoChiTietMapper;
import com.example.qlbhbe.service.congnochitiet.CongNoChiTietService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/congNoChiTiets")
@Validated
public class CongNoChiTietController {

    private final CongNoChiTietService congNoChiTietService;

    public CongNoChiTietController(CongNoChiTietService congNoChiTietService) {
        this.congNoChiTietService = congNoChiTietService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CongNoChiTietDTO command) {
        CongNoChiTiet congNoChiTiet = CongNoChiTietMapper.INSTANCE.create(command);
        congNoChiTietService.save(congNoChiTiet);
        return new CreatedIdResponse(congNoChiTiet.getId());
    }


    @PutMapping("/update")
    public void update(@PathVariable("id") long id, @Valid @RequestBody CongNoChiTietDTO command) {
        congNoChiTietService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        congNoChiTietService.deleteById(id);
    }

}
