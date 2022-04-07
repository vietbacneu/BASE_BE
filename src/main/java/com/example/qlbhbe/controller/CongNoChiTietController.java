package com.example.qlbhbe.controller;

import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.service.congnochitiet.CongNoChiTietService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API + "/congNoChiTiets")
@Validated
public class CongNoChiTietController {

    private final CongNoChiTietService congNoChiTietService;

    public CongNoChiTietController(CongNoChiTietService congNoChiTietService) {
        this.congNoChiTietService = congNoChiTietService;
    }


    @PostMapping("/search")
    public Page<CongNoChiTietDTO> list(@RequestBody CongNoChiTietDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return congNoChiTietService.search(params, pageable);
    }


    @GetMapping("{id}")
    public void update(@PathVariable(name = "id") long id) {
        congNoChiTietService.update(id);
    }

}
