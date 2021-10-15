package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateNhaCungCapRequest;
import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.controller.request.searchparams.NhaCungCapSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.NhaCungCap;
import com.example.qlbhbe.mapper.NhaCungCapMapper;
import com.example.qlbhbe.service.nhacungcap.NhaCungCapService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhaCungCaps")
@Validated
public class NhaCungCapController {

    private final NhaCungCapService nhaCungCapService;

    public NhaCungCapController(NhaCungCapService nhaCungCapService) {
        this.nhaCungCapService = nhaCungCapService;
    }

    @PostMapping("/search")
    public Page<NhaCungCapDTO> search(@RequestBody NhaCungCapDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhaCungCapService.search(command, pageable);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateNhaCungCapRequest command) {
        NhaCungCap nhaCungCap = NhaCungCapMapper.INSTANCE.create(command);
        nhaCungCapService.save(nhaCungCap);
        return new CreatedIdResponse(nhaCungCap.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateNhaCungCapRequest command) {
        nhaCungCapService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhaCungCapService.deleteById(id);
    }

}
