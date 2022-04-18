package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.service.congno.CongNoService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(Constants.API + "/congNos")
@Validated
public class CongNoController {

    private final CongNoService congNoService;


    public CongNoController(CongNoService congNoService) {
        this.congNoService = congNoService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CongNoDTO command) {
        return congNoService.create(command);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody CongNoDTO command) {
        congNoService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        congNoService.delete(id);
    }

    @PostMapping("/search")
    public Page<CongNoDTO> list(@RequestBody CongNoDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return congNoService.search(params, pageable);
    }

    @PostMapping("/searchForExport")
    public Page<CongNoDTO> searchForExport(@RequestBody CongNoDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return congNoService.searchForExport(params, pageable);
    }

    @PostMapping("/export")
    public Map<String, String> exportNhapMax(@RequestBody CongNoDTO sanPhamDTO) throws Exception {
        return congNoService.export(sanPhamDTO);
    }

}
