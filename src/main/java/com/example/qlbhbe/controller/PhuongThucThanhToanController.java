package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.PhuongThucThanhToanDTO;
import com.example.qlbhbe.entity.PhuongThucThanhToan;
import com.example.qlbhbe.mapper.PhuongThucThanhToanMapper;
import com.example.qlbhbe.service.phuongthucthanhtoan.PhuongThucThanhToanService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/phuongThucThanhToans")
@Validated
public class PhuongThucThanhToanController {

    private final PhuongThucThanhToanService phuongThucThanhToanService;


    public PhuongThucThanhToanController(PhuongThucThanhToanService phuongThucThanhToanService) {
        this.phuongThucThanhToanService = phuongThucThanhToanService;
    }

    @PostMapping
    public CreatedIdResponse create(PhuongThucThanhToanDTO command) {
        PhuongThucThanhToan phuongThucThanhToan = PhuongThucThanhToanMapper.INSTANCE.create(command);
        phuongThucThanhToanService.save(phuongThucThanhToan);
        return new CreatedIdResponse(phuongThucThanhToan.getId());
    }

    @PostMapping("/search")
    public Page<PhuongThucThanhToanDTO> list(@RequestBody PhuongThucThanhToanDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return phuongThucThanhToanService.searchAllPPTT(params, pageable);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody PhuongThucThanhToanDTO command) {
        phuongThucThanhToanService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        phuongThucThanhToanService.deleteById(id);
    }

}
