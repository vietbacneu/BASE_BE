package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.mapper.KhenThuongMapper;
import com.example.qlbhbe.service.khenthuong.KhenThuongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.API + "/khenThuongs")
@Validated
public class KhenThuongController {

    private final KhenThuongService khenThuongService;

    public KhenThuongController(KhenThuongService khenThuongService) {
        this.khenThuongService = khenThuongService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody KhenThuongDTO command) {
        KhenThuong khenThuong = KhenThuongMapper.INSTANCE.create(command);
        khenThuongService.save(khenThuong);
        return new CreatedIdResponse(khenThuong.getId());
    }

    @PostMapping("/search")
    public Page<KhenThuongDTO> search(@RequestBody(required = false) KhenThuongDTO command, @PageableDefault Pageable pageable) throws Exception {
        return khenThuongService.search(command, pageable);
    }

    @PutMapping("/update")
    public void update(@RequestBody KhenThuongDTO command) {
        khenThuongService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        khenThuongService.deleteById(id);
    }

}
