package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateChucVuRequest;
import com.example.qlbhbe.controller.request.UpdateChucVuRequest;
import com.example.qlbhbe.controller.request.searchparams.ChucVuSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.mapper.ChucVuMapper;
import com.example.qlbhbe.service.chucvu.ChucVuService;
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
@RequestMapping(Constants.API + "/chucVus")
@Validated
public class ChucVuController {

    private final ChucVuService chucVuService;

    public ChucVuController(ChucVuService chucVuService) {
        this.chucVuService = chucVuService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody ChucVuDTO command) {
        ChucVu chucVu = ChucVuMapper.INSTANCE.create(command);
        chucVuService.save(chucVu);
        return new CreatedIdResponse(chucVu.getId());
    }


    @PutMapping("/update")
    public void update( @Valid @RequestBody ChucVuDTO command) {
        chucVuService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        chucVuService.deleteById(id);
    }

}
