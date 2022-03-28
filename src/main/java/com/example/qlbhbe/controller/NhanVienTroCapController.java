package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.NhanVienTroCapMapper;
import com.example.qlbhbe.service.nhanvientrocap.NhanVienTroCapService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.DataUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(Constants.API + "/nhanVienTroCaps")
@Validated
public class NhanVienTroCapController {

    private final NhanVienTroCapService nhanVienTroCapService;

    public NhanVienTroCapController(NhanVienTroCapService nhanVienTroCapService) {
        this.nhanVienTroCapService = nhanVienTroCapService;
    }

    @PostMapping("/search")
    public Page<NhanVienTroCapDTO> search(@RequestBody(required = false) NhanVienTroCapDTO command, @PageableDefault Pageable pageable) throws Exception {
        return nhanVienTroCapService.search(command, pageable);
    }


    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody NhanVienTroCapDTO command) throws ParseException {
        NhanVienTroCap nhanVienTroCap = NhanVienTroCapMapper.INSTANCE.create(command);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(command.getIdNhanVien());
        nhanVienTroCap.setNhanVien(nhanVien);
        TroCap kt = new TroCap();
        kt.setId(command.getTroCap());
        nhanVienTroCap.setIdTroCap(kt);
        nhanVienTroCapService.save(nhanVienTroCap);
        return new CreatedIdResponse(nhanVienTroCap.getId());
    }

    @PutMapping("/update")
    public void update (@RequestBody NhanVienTroCapDTO command) throws ParseException {
        nhanVienTroCapService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienTroCapService.deleteById(id);
    }

}
