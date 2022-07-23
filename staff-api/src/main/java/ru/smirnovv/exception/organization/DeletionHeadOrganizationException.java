package ru.smirnovv.exception.organization;

import ru.smirnovv.exception.ApplicationException;

public class DeletionHeadOrganizationException extends ApplicationException {

    private static final String message = "Невозможно удалить головную организацию";

    /**
     * Конструктор экземпляра с информацией об ошибке
     */
    public DeletionHeadOrganizationException() {
        super(message);
    }

}
