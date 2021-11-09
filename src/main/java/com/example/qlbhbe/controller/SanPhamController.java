package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.mapper.SanPhamMapper;
import com.example.qlbhbe.service.baocao.SanPhamReport;
import com.example.qlbhbe.service.sanpham.SanPhamService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.API + "/sanPhams")
@Validated
public class SanPhamController {

    private final SanPhamService sanPhamService;
    private final SanPhamReport sanPhamReport;

    public SanPhamController(SanPhamService sanPhamService, SanPhamReport sanPhamReport) {
        this.sanPhamService = sanPhamService;
        this.sanPhamReport = sanPhamReport;
    }


    @PostMapping("/search")
    public Page<SanPhamDTO> search(@RequestBody(required = false) SanPhamDTO command, @PageableDefault Pageable pageable) throws Exception {
        return sanPhamService.search(command, pageable);
    }

    @PostMapping("/searchTon")
    public List<SanPhamDTO> searchTon(SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamTon(command);
    }

    @PostMapping("/searchTongChiPhi")
    public List<SanPhamDTO> searchTongChiPhi(SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamChiPhiMax(command);
    }

    @PostMapping("/searchTongDoanhThu")
    public List<SanPhamDTO> searchTongDoanhThu(SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamDoanhThuMax(command);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody SanPhamDTO command) {
        SanPham sanPham = SanPhamMapper.INSTANCE.create(command);
        sanPhamService.save(sanPham);
        return new CreatedIdResponse(sanPham.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody SanPhamDTO command) {
        sanPhamService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        sanPhamService.deleteById(id);
    }

}
