package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateXuatHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.controller.request.searchparams.XuatHangChiTietSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.mapper.XuatHangChiTietMapper;
import com.example.qlbhbe.service.xuathangchitiet.XuatHangChiTietService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/xuatHangChiTiets")
@Validated
public class XuatHangChiTietController {

    private final XuatHangChiTietService xuatHangChiTietService;

    public XuatHangChiTietController(XuatHangChiTietService xuatHangChiTietService) {
        this.xuatHangChiTietService = xuatHangChiTietService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateXuatHangChiTietRequest command) {
        XuatHangChiTiet xuatHangChiTiet = XuatHangChiTietMapper.INSTANCE.create(command);
        xuatHangChiTietService.save(xuatHangChiTiet);
        return new CreatedIdResponse(xuatHangChiTiet.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateXuatHangChiTietRequest command) {
        xuatHangChiTietService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        xuatHangChiTietService.deleteById(id);
    }

}
