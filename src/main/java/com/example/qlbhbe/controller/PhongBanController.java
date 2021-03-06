package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.mapper.PhongBanMapper;
import com.example.qlbhbe.service.phongban.PhongBanService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/phongBans")
@Validated
public class PhongBanController {

    private final PhongBanService phongBanService;


    public PhongBanController(PhongBanService phongBanService) {
        this.phongBanService = phongBanService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody PhongBanDTO command) {
        PhongBan phongBan = PhongBanMapper.INSTANCE.create(command);
        phongBanService.save(phongBan);
        return new CreatedIdResponse(phongBan.getId());
    }

    @PostMapping("/search")
    public Page<PhongBanDTO> search(@RequestBody(required = false) PhongBanDTO command, @PageableDefault Pageable pageable) throws Exception {
        return phongBanService.search(command, pageable);
    }

    @PutMapping("/update")
    public void update(@RequestBody PhongBanDTO command) {
        phongBanService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        phongBanService.deleteById(id);
    }

}
