package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateXuatHangRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.service.xuathang.XuatHangService;
import com.example.qlbhbe.util.Constants;
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


    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateXuatHangRequest command) {
        xuatHangService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        xuatHangService.deleteById(id);
    }

}
