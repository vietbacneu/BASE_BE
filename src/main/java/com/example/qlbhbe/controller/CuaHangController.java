package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.entity.CuaHang;
import com.example.qlbhbe.mapper.CuaHangMapper;
import com.example.qlbhbe.service.cuahang.CuaHangDetailsService;
import com.example.qlbhbe.service.cuahang.CuaHangSearchService;
import com.example.qlbhbe.service.cuahang.CuaHangService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/cuaHangs")
@Validated
public class CuaHangController {

    private final CuaHangService cuaHangService;
    private final CuaHangSearchService cuaHangSearchService;
    private final CuaHangDetailsService cuaHangDetailsService;

    public CuaHangController(CuaHangService cuaHangService, CuaHangSearchService cuaHangSearchService, CuaHangDetailsService cuaHangDetailsService) {
        this.cuaHangService = cuaHangService;
        this.cuaHangSearchService = cuaHangSearchService;
        this.cuaHangDetailsService = cuaHangDetailsService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CuaHangDTO command) {
        CuaHang cuaHang = CuaHangMapper.INSTANCE.create(command);
        cuaHangService.save(cuaHang);
        return new CreatedIdResponse(cuaHang.getId());
    }

    @GetMapping
    public Page<CuaHangDTO> list(CuaHangDTO params, Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return cuaHangService.search(params, pageable);
    }

    @PutMapping("/update")
    public void update(@PathVariable("id") long id, @Valid @RequestBody CuaHangDTO command) {
        cuaHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        cuaHangService.deleteById(id);
    }

}
