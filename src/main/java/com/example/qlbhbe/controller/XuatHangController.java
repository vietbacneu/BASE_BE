package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateXuatHangRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.service.xuathang.XuatHangService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/xuatHangs")
@Validated
public class XuatHangController {

    private final XuatHangService xuatHangService;

    public XuatHangController(XuatHangService xuatHangService) {
        this.xuatHangService = xuatHangService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateXuatHangRequest command) {
        XuatHang xuatHang = XuatHangMapper.INSTANCE.create(command);
        xuatHangService.save(xuatHang);
        return new CreatedIdResponse(xuatHang.getId());
    }

    @PostMapping("/search")
    public Page<XuatHangDTO> search(@RequestBody XuatHangDTO command, @PageableDefault Pageable pageable) throws Exception {
        return xuatHangService.search(command, pageable);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateXuatHangRequest command) {
        xuatHangService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        xuatHangService.deleteById(id);
    }

}
