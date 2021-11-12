package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.UpdateNhanVienBaoHiemRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import com.example.qlbhbe.mapper.NhanVienBaoHiemMapper;
import com.example.qlbhbe.service.nhanvienbaohiem.NhanVienBaoHiemService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhanVienBaoHiems")
@Validated
public class NhanVienBaoHiemController {

    private final NhanVienBaoHiemService nhanVienBaoHiemService;

    public NhanVienBaoHiemController(NhanVienBaoHiemService nhanVienBaoHiemService) {
        this.nhanVienBaoHiemService = nhanVienBaoHiemService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienBaoHiemDTO command) {
        NhanVienBaoHiem nhanVienBaoHiem = NhanVienBaoHiemMapper.INSTANCE.create(command);
        nhanVienBaoHiemService.save(nhanVienBaoHiem);
        return new CreatedIdResponse(nhanVienBaoHiem.getId());
    }

    @PostMapping("/search")
    public Page<NhanVienBaoHiemDTO> search(@RequestBody(required = false) NhanVienBaoHiemDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhanVienBaoHiemService.search(command, pageable);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody NhanVienBaoHiemDTO command) {
        nhanVienBaoHiemService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienBaoHiemService.deleteById(id);
    }

}
