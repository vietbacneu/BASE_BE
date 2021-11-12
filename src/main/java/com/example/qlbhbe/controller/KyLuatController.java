package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.mapper.KyLuatMapper;
import com.example.qlbhbe.service.kyluat.KyLuatService;
import com.example.qlbhbe.util.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/kyLuats")
@Validated
public class KyLuatController {

    private final KyLuatService kyLuatService;

    public KyLuatController(KyLuatService kyLuatService) {
        this.kyLuatService = kyLuatService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody KyLuatDTO command) {
        KyLuat kyLuat = KyLuatMapper.INSTANCE.create(command);
        kyLuatService.save(kyLuat);
        return new CreatedIdResponse(kyLuat.getId());
    }

    @PutMapping("{id}")
    public void update(@RequestBody KyLuatDTO command) {
        kyLuatService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        kyLuatService.deleteById(id);
    }

}
