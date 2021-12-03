package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.mapper.KhenThuongMapper;
import com.example.qlbhbe.service.khenthuong.KhenThuongService;
import com.example.qlbhbe.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.API + "/khenThuongs")
@Validated
public class KhenThuongController {

//    public static void main(String[] args) {
////        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMyyyy");
////        for (LocalDate start = LocalDate.now().plusMonths(-60); start.isBefore(LocalDate.now().plusMonths(2)); start = start.plusDays(1)) {
////            System.out.println(" ALTER TABLE ROAMING_IMSI ADD PARTITION roamingbi" + start.format(formatters) + " VALUES LESS THAN (TO_DATE('" + start.format(formatters) + "','ddMMyyyy'));");
////        }
//        String str = ("NOT_AVAI-linus,            NOT_AVAI-linus \n           \t" +
//                ",            \n           \t \nNOT_AVAI-linus").replaceAll("\\s+","");
//        String[] tmp = str.trim().split(",");
//        List<String> stringList = new ArrayList<>();
//        for (String s : tmp) {
//            StringBuilder builder = new StringBuilder();
//            String[] words = s.split("[\\W_]+");
//            for (int i = 0; i < words.length; i++) {
//                String word = words[i];
//                if (i == 0) {
//                    word = word.isEmpty() ? word : word.toLowerCase();
//                } else {
//                    word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
//                }
//                builder.append(word);
//            }
//            stringList.add('"' + builder.toString() + '"');
//        }
//        System.out.println(String.join(",", stringList));
//    }


    private final KhenThuongService khenThuongService;

    public KhenThuongController(KhenThuongService khenThuongService) {
        this.khenThuongService = khenThuongService;
    }

    @PostMapping
    public CreatedIdResponse create(@Valid @RequestBody KhenThuongDTO command) {
        KhenThuong khenThuong = KhenThuongMapper.INSTANCE.create(command);
        khenThuongService.save(khenThuong);
        return new CreatedIdResponse(khenThuong.getId());
    }

    @PostMapping("/search")
    public Page<KhenThuongDTO> search(@RequestBody(required = false) KhenThuongDTO command, @PageableDefault Pageable pageable) throws Exception {
        return khenThuongService.search(command, pageable);
    }

    @PutMapping("/update")
    public void update(@RequestBody KhenThuongDTO command) {
        khenThuongService.update(command.getId(), command);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") long id) {
        khenThuongService.deleteById(id);
    }

}
