package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.TroCapMapper;
import com.example.qlbhbe.service.trocap.TroCapService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API + "/troCaps")
@Validated
public class TroCapController {

    private final TroCapService troCapService;

    public TroCapController(TroCapService troCapService) {
        this.troCapService = troCapService;
    }

    @PostMapping("/search")
    public Page<TroCapDTO> search(@RequestBody(required = false) TroCapDTO command, @PageableDefault Pageable pageable) throws Exception {
        return troCapService.search(command, pageable);
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody TroCapDTO command) {
        TroCap troCap = TroCapMapper.INSTANCE.create(command);
        troCapService.save(troCap);
        return new CreatedIdResponse(troCap.getId());
    }

    @PutMapping("/update")
    public void update( @Valid @RequestBody TroCapDTO command) {
        troCapService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        troCapService.deleteById(id);
    }

}
