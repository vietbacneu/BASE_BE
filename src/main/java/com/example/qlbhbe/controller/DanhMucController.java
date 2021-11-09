package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateDanhMucRequest;
import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.mapper.DanhMucMapper;
import com.example.qlbhbe.service.danhmuc.DanhMucService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API + "/danhMucs")
@Validated
public class DanhMucController {

    private final DanhMucService danhMucService;


    public DanhMucController(DanhMucService danhMucService) {
        this.danhMucService = danhMucService;
    }

    @PostMapping("/search")
    public Page<DanhMucDTO> search(@RequestBody(required = false) DanhMucDTO command, @PageableDefault Pageable pageable) throws Exception {
            return danhMucService.searchAllDanhMuc(command, pageable);
    }

    @PostMapping
    public CreatedIdResponse create(@RequestBody DanhMucDTO command) {
        DanhMuc danhMuc = DanhMucMapper.INSTANCE.create(command);
        danhMucService.save(danhMuc);
        return new CreatedIdResponse(danhMuc.getId());
    }

    @PutMapping("/update")
    public void update(@PathVariable("id") long id, @RequestBody DanhMucDTO command) {
        danhMucService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        danhMucService.deleteById(id);
    }

}
