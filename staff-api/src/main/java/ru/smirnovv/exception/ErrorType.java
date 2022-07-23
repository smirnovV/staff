package ru.smirnovv.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, содержащий информацию об ошибке.
 */
@AllArgsConstructor
public class ErrorType {

    /**
     * Запрос, где произошла ошибка.
     */
    @Getter
    private final String url;

    /**
     * HTTP статус.
     */
    @Getter
    private final int status;

    /**
     * Детальное сообщение.
     */
    @Getter
    private final String message;

}
