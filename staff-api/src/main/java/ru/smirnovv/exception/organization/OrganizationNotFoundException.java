package ru.smirnovv.exception.organization;

import ru.smirnovv.exception.ApplicationException;

public class OrganizationNotFoundException extends ApplicationException {

    private static final String message = "Организация не найдена УИД = ";

    /**
     * Конструктор экземпляра с информацией об ошибке
     *
     * @param id УИД организации
     */
    public OrganizationNotFoundException(Long id) {
        super(message + id);
    }

}
