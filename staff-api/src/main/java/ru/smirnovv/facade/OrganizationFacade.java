package ru.smirnovv.facade;

import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.controller.dto.PageInfo;

import java.util.List;

public interface OrganizationFacade {

    /**
     * Создать организацию
     *
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    void create(final String name, final Long headOrganizationId);

    /**
     * Удалить организацию
     *
     * @param id УИД организации
     */
    void delete(final long id);

    /**
     * Обновить сведения об организации
     *
     * @param id                 УИД организации
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    void update(final long id, final String name, final Long headOrganizationId);

    /**
     * Получить лист организации
     *
     * @param pageInfo пагинация страницы
     * @return лист организации
     */
    List<OrganizationDTO> getPage(final PageInfo pageInfo);

    /**
     * Получить список организации
     *
     * @return список организации
     */
    List<OrganizationDTO> getAll();

}
