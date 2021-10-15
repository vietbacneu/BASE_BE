package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateNhapHangRequest;
import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.controller.request.searchparams.NhapHangSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.mapper.NhapHangMapper;
import com.example.qlbhbe.service.nhaphang.NhapHangService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhapHangs")
@Validated
public class NhapHangController {

    private final NhapHangService nhapHangService;

    public NhapHangController(NhapHangService nhapHangService) {
        this.nhapHangService = nhapHangService;
    }


    @PostMapping("/search")
    public Page<NhapHangDTO> search(@RequestBody NhapHangDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhapHangService.search(command, pageable);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateNhapHangRequest command) {
        NhapHang nhapHang = NhapHangMapper.INSTANCE.create(command);
        nhapHangService.save(nhapHang);
        return new CreatedIdResponse(nhapHang.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateNhapHangRequest command) {
        nhapHangService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhapHangService.deleteById(id);
    }

}
