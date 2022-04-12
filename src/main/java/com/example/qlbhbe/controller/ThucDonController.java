package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.dto.ThucDonDTO;
import com.example.qlbhbe.entity.ThucDon;
import com.example.qlbhbe.mapper.ThucDonMapper;
import com.example.qlbhbe.service.thucdon.ThucDonService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

;
;

@RestController
@RequestMapping(Constants.API + "/thucDons")
@Validated
public class ThucDonController {

    private final ThucDonService thucDonService;

    public ThucDonController(ThucDonService thucDonService) {
        this.thucDonService = thucDonService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody ThucDonDTO command) {
        ThucDon thucDon = ThucDonMapper.INSTANCE.create(command);
        thucDonService.save(thucDon);
        return new CreatedIdResponse(thucDon.getId());
    }

    @PostMapping("/search")
    public Page<ThucDonDTO> list(@RequestBody ThucDonDTO params, @PageableDefault Pageable pageable) throws Exception {
        pageable = Utils.getDefaultSortPageable(pageable);
        return thucDonService.search(params, pageable);
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody ThucDonDTO command) {
        thucDonService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        thucDonService.deleteById(id);
    }

}
