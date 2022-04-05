package ru.smirnovv.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.service.OrganizationService;

import java.util.List;

/**
 * Контроллер управления сведениями об организациях
 */
@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    /**
     * Сервис управления сведениями об организациях
     */
    private final OrganizationService organizationService;

    /**
     * Создать организацию
     *
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    @PutMapping("/create")
    public void create(@RequestParam final String name, @RequestParam final Long headOrganizationId) {
        organizationService.create(name, headOrganizationId);
    }

    /**
     * Удалить организацию
     *
     * @param id УИД организации
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        organizationService.delete(id);
    }

    /**
     * Обновить сведения об организации
     *
     * @param id                 УИД организации
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    @PutMapping("/{id}")
    public void update(@PathVariable final long id,
                       @RequestParam final String name,
                       @RequestParam final Long headOrganizationId) {
        organizationService.update(id, name, headOrganizationId);
    }

    /**
     * Получить лист организации
     *
     * @param pageable пагинация страницы
     * @return лист организации
     */
    @GetMapping("")
    public List<Organization> getPage(@PageableDefault(sort = "id") final Pageable pageable) {
        return organizationService.getPage(pageable);
    }

}
