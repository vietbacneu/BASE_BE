package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateSanPhamRequest;
import com.example.qlbhbe.controller.request.UpdateSanPhamRequest;
import com.example.qlbhbe.controller.request.searchparams.SanPhamSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.mapper.SanPhamMapper;
import com.example.qlbhbe.service.sanpham.SanPhamService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/sanPhams")
@Validated
public class SanPhamController {

    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }


    @PostMapping("/search")
    public Page<SanPhamDTO> search(@RequestBody(required = false) SanPhamDTO command, @PageableDefault Pageable pageable) throws Exception {
        return sanPhamService.search(command, pageable);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateSanPhamRequest command) {
        SanPham sanPham = SanPhamMapper.INSTANCE.create(command);
        sanPhamService.save(sanPham);
        return new CreatedIdResponse(sanPham.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateSanPhamRequest command) {
        sanPhamService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        sanPhamService.deleteById(id);
    }

}
