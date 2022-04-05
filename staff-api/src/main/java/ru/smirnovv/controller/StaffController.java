package ru.smirnovv.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnovv.service.StaffService;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PutMapping("/create")
    public void create(@RequestParam final String name,
                       @RequestParam final Long bossId,
                       @RequestParam final Long organizationId) {
        staffService.create(name, bossId, organizationId);
    }

}
