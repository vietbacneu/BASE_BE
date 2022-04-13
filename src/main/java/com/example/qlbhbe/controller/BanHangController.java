package com.example.qlbhbe.controller;

import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.service.banhang.BanHangService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping(Constants.API + "/banHangs")
@Validated
public class BanHangController {

    private final BanHangService banHangService;

    public BanHangController(BanHangService banHangService) {
        this.banHangService = banHangService;
    }

    @PostMapping
    public MessageDTO create(@Valid @RequestBody BanHangDTO command) {
        return banHangService.save(command);
    }


    @PostMapping("/search")
    public Page<BanHangDTO> search(@RequestBody BanHangDTO command, @PageableDefault Pageable pageable) throws Exception {
        return banHangService.search(command, pageable);
    }

    @PostMapping("/export")
    public Map<String, String> export(@RequestBody BanHangDTO sanPhamDTO) throws Exception {
        return banHangService.export(sanPhamDTO);
    }


    @PutMapping("/update")
    public void update(@Valid @RequestBody BanHangDTO command) {
        banHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        banHangService.deleteById(id);
    }

}
