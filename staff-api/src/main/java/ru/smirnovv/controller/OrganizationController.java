package ru.smirnovv.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PutMapping("/create")
    public void create(@RequestParam final String name, @RequestParam final Long headOrganizationId) {
        organizationService.create(name, headOrganizationId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        organizationService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final long id,
                       @RequestParam final String name,
                       @RequestParam final Long headOrganizationId) {
        organizationService.update(id, name, headOrganizationId);
    }

    @GetMapping("/")
    public List<Organization> getPage(@PageableDefault(sort = "id") final Pageable pageable) {
        return organizationService.getPage(pageable);
    }

}
