package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.TroCapMapper;
import com.example.qlbhbe.service.trocap.TroCapService;
import com.example.qlbhbe.service.trocap.TroCapSearchService;
import com.example.qlbhbe.util.Constants;
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
    private final TroCapSearchService troCapSearchService;
    private final TroCapDetailsService troCapDetailsService;

    public TroCapController(TroCapService troCapService, TroCapSearchService troCapSearchService, TroCapDetailsService troCapDetailsService) {
        this.troCapService = troCapService;
        this.troCapSearchService = troCapSearchService;
        this.troCapDetailsService = troCapDetailsService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody TroCapDTO command) {
        TroCap troCap = TroCapMapper.INSTANCE.create(command);
        troCapService.save(troCap);
        return new CreatedIdResponse(troCap.getId());
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody TroCapDTO command) {
        troCapService.update(id, command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        troCapService.deleteById(id);
    }

}
