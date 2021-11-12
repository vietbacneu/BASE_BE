package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.UpdateKhenThuongRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.mapper.KhenThuongMapper;
import com.example.qlbhbe.service.khenthuong.KhenThuongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping("/update")
    public void update(@RequestBody KhenThuongDTO command) {
        khenThuongService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        khenThuongService.deleteById(id);
    }

}
