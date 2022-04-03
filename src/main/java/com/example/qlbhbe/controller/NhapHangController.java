package com.example.qlbhbe.controller;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.service.nhaphang.NhapHangService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    @PostMapping(value = "/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String message = "";
        try {
            String filePath = "D:\\" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(filePath));
            Map<String, String> result = new HashMap<>();
            result.put("duongDan", filePath);
            result.put("hopDongDinhKem", multipartFile.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            throw e;
        }
    }

}
