package com.example.qlbhbe.controller;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.service.nhaphang.NhapHangService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.API + "/nhapHangs")
@Validated
public class NhapHangController {

    private final NhapHangService nhapHangService;

    public NhapHangController(NhapHangService nhapHangService) {
        this.nhapHangService = nhapHangService;
    }


    @PostMapping("/search")
    public Page<NhapHangDTO> search(@RequestBody NhapHangDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhapHangService.search(command, pageable);
    }

    @PostMapping("/searchNhapMax")
    public List<NhapHangDTO> searchNhapMax(@RequestBody NhapHangDTO command) throws Exception {
        return nhapHangService.getNhapHangMax(command);
    }

    @PostMapping("/exportNhapMax")
    public Map<String, String> exportNhapMax(@RequestBody NhapHangDTO sanPhamDTO) throws Exception {
        return nhapHangService.exportNhapMax(sanPhamDTO);
    }

    @PostMapping
    public MessageDTO create(@Valid @RequestBody NhapHangDTO command) {
        return nhapHangService.save(command);
    }

    @PostMapping("/update")
    public MessageDTO update(@Valid @RequestBody NhapHangDTO command) {
        return nhapHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhapHangService.deleteById(id);
    }

}
