package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateNhapHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.mapper.NhapHangChiTietMapper;
import com.example.qlbhbe.service.nhaphangchitiet.NhapHangChiTietService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.API + "/nhapHangChiTiets")
@Validated
public class NhapHangChiTietController {

    private final NhapHangChiTietService nhapHangChiTietService;

    public NhapHangChiTietController(NhapHangChiTietService nhapHangChiTietService) {
        this.nhapHangChiTietService = nhapHangChiTietService;
    }

    @PostMapping("/search")
    public List<NhapHangChiTietDTO> search(@RequestBody(required = false) NhapHangChiTietDTO command) throws Exception {
        return nhapHangChiTietService.search(command);
    }


    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateNhapHangChiTietRequest command) {
        NhapHangChiTiet nhapHangChiTiet = NhapHangChiTietMapper.INSTANCE.create(command);
        nhapHangChiTietService.save(nhapHangChiTiet);
        return new CreatedIdResponse(nhapHangChiTiet.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateNhapHangChiTietRequest command) {
        nhapHangChiTietService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhapHangChiTietService.deleteById(id);
    }

}
