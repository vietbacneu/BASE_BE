package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateNhanVienTroCapRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienTroCapRequest;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.mapper.NhanVienTroCapMapper;
import com.example.qlbhbe.service.nhanvientrocap.NhanVienTroCapService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/nhanVienTroCaps")
@Validated
public class NhanVienTroCapController {

    private final NhanVienTroCapService nhanVienTroCapService;

    public NhanVienTroCapController(NhanVienTroCapService nhanVienTroCapService) {
        this.nhanVienTroCapService = nhanVienTroCapService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody CreateNhanVienTroCapRequest command) {
        NhanVienTroCap nhanVienTroCap = NhanVienTroCapMapper.INSTANCE.create(command);
        nhanVienTroCapService.save(nhanVienTroCap);
        return new CreatedIdResponse(nhanVienTroCap.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody UpdateNhanVienTroCapRequest command) {
        nhanVienTroCapService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        nhanVienTroCapService.deleteById(id);
    }

}
