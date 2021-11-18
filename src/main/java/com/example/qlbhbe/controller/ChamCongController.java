package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.ChamCongMapper;
import com.example.qlbhbe.service.chamcong.ChamCongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(Constants.API + "/chamCongs")
@Validated
public class ChamCongController {

    private final ChamCongService chamCongService;

    public ChamCongController(ChamCongService chamCongService) {
        this.chamCongService = chamCongService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody ChamCongDTO command) {
        ChamCong chamCong = ChamCongMapper.INSTANCE.create(command);
        NhanVien nhanVien =  new NhanVien();
        nhanVien.setId(command.getIdNhanVien());
        chamCong.setNhanVien(nhanVien);
        chamCongService.save(chamCong);
        return new CreatedIdResponse(chamCong.getId());
    }

    @PostMapping("/search")
    public Page<ChamCongDTO> search(@RequestBody(required = false) ChamCongDTO command, @PageableDefault Pageable pageable) throws Exception {
        return chamCongService.search(command, pageable);
    }

    @PostMapping("/searchPhieuLuong")
    public Page<ChamCongDTO> searchPhieuLuong(@RequestBody(required = false) ChamCongDTO command, @PageableDefault Pageable pageable) throws Exception {
        return chamCongService.searchPhieuLuong(command, pageable);
    }

    @PostMapping("/exportPhieuLuong")
    public Map<String,String> exportPhieuLuong(@RequestBody(required = false) ChamCongDTO command) throws Exception {
        return chamCongService.exportPhieuLuong(command);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody ChamCongDTO command) {
        chamCongService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        chamCongService.deleteById(id);
    }

}
