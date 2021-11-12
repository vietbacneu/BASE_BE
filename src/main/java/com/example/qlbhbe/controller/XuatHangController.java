package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateXuatHangRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.service.xuathang.XuatHangService;
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
@RequestMapping(Constants.API + "/xuatHangs")
@Validated
public class XuatHangController {

    private final XuatHangService xuatHangService;

    public XuatHangController(XuatHangService xuatHangService) {
        this.xuatHangService = xuatHangService;
    }

    @PostMapping("/search")
    public Page<XuatHangDTO> search(@RequestBody XuatHangDTO command, @PageableDefault Pageable pageable) throws Exception {
        return xuatHangService.search(command, pageable);
    }

    @PostMapping("/searchXuatMax")
    public List<XuatHangDTO> searchXuatMax(@RequestBody XuatHangDTO command) throws Exception {
        return xuatHangService.searchXuatMax(command);
    }

    @PostMapping("/exportXuatMax")
    public Map<String, String> exportXuatMax(@RequestBody XuatHangDTO command) throws Exception {
        return xuatHangService.exportXuatMax(command);
    }

    @PostMapping
    public MessageDTO create(@Valid @RequestBody XuatHangDTO command) {
        return xuatHangService.save(command);
    }

    @PutMapping("/update")
    public void update( @Valid @RequestBody XuatHangDTO command) {

        xuatHangService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        xuatHangService.deleteById(id);
    }

}
