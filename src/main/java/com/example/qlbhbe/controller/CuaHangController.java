package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.entity.CuaHang;
import com.example.qlbhbe.mapper.CuaHangMapper;
import com.example.qlbhbe.service.cuahang.CuaHangService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/cuaHangs")
@Validated
public class CuaHangController {

    private final CuaHangService cuaHangService;


    public CuaHangController(CuaHangService cuaHangService) {
        this.cuaHangService = cuaHangService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CuaHangDTO command) {
        CuaHang cuaHang = CuaHangMapper.INSTANCE.create(command);
        cuaHangService.save(cuaHang);
        return new CreatedIdResponse(cuaHang.getId());
    }

    @PostMapping("/search")
    public Page<CuaHangDTO> list(@RequestBody CuaHangDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return cuaHangService.search(params, pageable);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody CuaHangDTO command) {
        cuaHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        cuaHangService.deleteById(id);
    }

}
