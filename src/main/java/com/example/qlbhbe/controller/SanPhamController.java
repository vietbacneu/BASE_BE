package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.mapper.SanPhamMapper;
import com.example.qlbhbe.service.baocao.PDF;
import com.example.qlbhbe.service.baocao.SanPhamReport;
import com.example.qlbhbe.service.sanpham.SanPhamService;
import com.example.qlbhbe.util.Constants;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.API + "/sanPhams")
@Validated
public class SanPhamController {

    private final SanPhamService sanPhamService;
    private final SanPhamReport sanPhamReport;
    private final PDF pdf;

    public SanPhamController(SanPhamService sanPhamService, SanPhamReport sanPhamReport, PDF pdf) {
        this.sanPhamService = sanPhamService;
        this.sanPhamReport = sanPhamReport;
        this.pdf = pdf;
    }


    @PostMapping("/search")
    public Page<SanPhamDTO> search(@RequestBody(required = false) SanPhamDTO command, @PageableDefault Pageable pageable) throws Exception {
        return sanPhamService.search(command, pageable);
    }

    @PostMapping("/searchTon")
    public List<SanPhamDTO> searchTon(@RequestBody SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamTon(command);
    }

    @PostMapping("/searchTongChiPhi")
    public List<SanPhamDTO> searchTongChiPhi(@RequestBody SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamChiPhiMax(command);
    }

    @PostMapping("/searchTongDoanhThu")
    public List<SanPhamDTO> searchTongDoanhThu(@RequestBody SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamDoanhThuMax(command);
    }

    @PostMapping("/sanPhamXuat")
    public List<SanPhamDTO> sanPhamXuat(@RequestBody SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamXuat(command);
    }

    @PostMapping("/sanPhamNhap")
    public List<SanPhamDTO> sanPhamNhap(@RequestBody SanPhamDTO command) throws Exception {
        return sanPhamReport.getSanPhamNhap(command);
    }


    @PostMapping("/exportTonKho")
    public Map<String, String> exportSanPhamTon(@RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        return sanPhamReport.exportSanPhamTon(sanPhamDTO);
    }
    @PostMapping("/exportTonKhoPDF")
    public Map<String, String> exportTonKhoPDF(@RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        return pdf.exportSanPhamTonPDF(sanPhamDTO);
    }


    @PostMapping("/exportDoanhThu")
    public Map<String, String> exportDoanhThu(@RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        return sanPhamReport.exportSanPhamDoanhThuMax(sanPhamDTO);
    }

    @PostMapping("/exportChiPhi")
    public Map<String, String> exportChiPhi(@RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        return sanPhamReport.exportSanPhamChiPhiMax(sanPhamDTO);
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download(@RequestParam String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        File file = new File(path);
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody SanPhamDTO command) {
        SanPham sanPham = SanPhamMapper.INSTANCE.create(command);
        sanPham.setDanhMuc(new DanhMuc(command.getIdDanhMuc()));
        sanPhamService.save(sanPham);
        return new CreatedIdResponse(sanPham.getId());
    }

    @PutMapping("/update")
    public void update(@RequestBody SanPhamDTO command) {
        sanPhamService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        sanPhamService.deleteById(id);
    }

}
