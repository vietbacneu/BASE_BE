package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.request.CreateBaoHiemRequest;
import com.example.qlbhbe.controller.request.UpdateBaoHiemRequest;
import com.example.qlbhbe.controller.request.searchparams.BaoHiemSearchParams;
import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.controller.response.PaginationDataResponse;
import com.example.qlbhbe.dto.BaoHiemDTO;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.BaoHiem;
import com.example.qlbhbe.mapper.BaoHiemMapper;
import com.example.qlbhbe.service.baohiem.BaoHiemService;
import com.example.qlbhbe.util.Constants;
import com.example.qlbhbe.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(Constants.API + "/baoHiems")
@Validated
public class BaoHiemController {

    private final BaoHiemService baoHiemService;

    public BaoHiemController(BaoHiemService baoHiemService) {
        this.baoHiemService = baoHiemService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody BaoHiemDTO command) {
        BaoHiem baoHiem = BaoHiemMapper.INSTANCE.create(command);
        baoHiemService.save(baoHiem);
        return new CreatedIdResponse(baoHiem.getId());
    }

    @PostMapping("/search")
    public Page<BaoHiemDTO> search(@RequestBody(required = false) BaoHiemDTO command, @PageableDefault Pageable pageable) throws Exception {
        return baoHiemService.search(command, pageable);
    }


    @PutMapping("/update")
    public void update( @RequestBody BaoHiemDTO command) {
        baoHiemService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasPermission('DELETE_BAO_HIEM')")
    public void delete(@PathVariable(name = "id") long id) {
        baoHiemService.deleteById(id);
    }

}
