package ru.smirnovv.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.controller.dto.PageInfo;
import ru.smirnovv.facade.OrganizationFacade;

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
    private final OrganizationFacade organizationFacade;

    /**
     * Создать организацию
     *
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    @PutMapping("/create")
    public void create(@RequestParam final String name, @RequestParam final Long headOrganizationId) {
        organizationFacade.create(name, headOrganizationId);
    }

    /**
     * Удалить организацию
     *
     * @param id УИД организации
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        organizationFacade.delete(id);
    }

    /**
     * Обновить сведения об организации
     *
     * @param id                 УИД организации
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    @PutMapping("/{id}")
    public void update(
            @PathVariable final long id, @RequestParam final String name, @RequestParam final Long headOrganizationId) {
        organizationFacade.update(id, name, headOrganizationId);
    }

    /**
     * Получить лист организации
     *
     * @param pageInfo пагинация страницы
     * @return лист организации
     */
    @GetMapping("")
    @CrossOrigin("http://localhost:3000")
    public List<OrganizationDTO> getPage(final PageInfo pageInfo) {
        return organizationFacade.getPage(pageInfo);
    }

    /**
     * Получить список организации
     *
     * @return список организации
     */
    @GetMapping("")
    @CrossOrigin("http://localhost:3000")
    public List<OrganizationDTO> getAll() {
        return organizationFacade.getAll();
    }

}
