package ru.smirnovv.service;

import org.springframework.data.domain.Pageable;
import ru.smirnovv.entity.Organization;

import java.util.List;

/**
 * Сервис работы с организациями
 */
public interface OrganizationService {

    /**
     * Создать организацию
     *
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    void create(String name, Long headOrganizationId);

    /**
     * Удалить организацию
     *
     * @param id УИД организации
     */
    void delete(long id);

    /**
     * Обновить организацию
     *
     * @param id                 УИД организации
     * @param name               название организации
     * @param headOrganizationId УИД головной организации
     */
    void update(long id, String name, Long headOrganizationId);

    /**
     * Получить страницу организации
     *
     * @param pageable данные пагинации
     * @return страница организации
     */
    List<Organization> getPage(Pageable pageable);
}
