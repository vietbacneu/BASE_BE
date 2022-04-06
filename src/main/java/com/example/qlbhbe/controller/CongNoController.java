package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.service.congno.CongNoService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void update(@PathVariable("id") long id, @Valid @RequestBody CongNoDTO command) {
        congNoService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        congNoService.deleteById(id);
    }

    @PostMapping("/search")
    public Page<CongNoDTO> list(@RequestBody CongNoDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return congNoService.search(params, pageable);
    }

}
