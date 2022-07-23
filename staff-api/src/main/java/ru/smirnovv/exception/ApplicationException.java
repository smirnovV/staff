package ru.smirnovv.exception;

/**
 * Абстрактный класс, возникающих ошибок
 */
public abstract class ApplicationException extends RuntimeException {

    /**
     * Конструктор экземпляра с информацией об ошибке
     *  @param message сообщение ошибки
     */
    public ApplicationException(String message) {
        super(message);
    }

}
