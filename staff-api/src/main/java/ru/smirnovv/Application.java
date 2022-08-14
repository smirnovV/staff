package ru.smirnovv;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Приложения для управления списком организации и сотрудниками
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

    /**
     * Точка входа.
     *
     * @param args программные аргументы.
     */
    public static void main(final String[] args) {

        new SpringApplicationBuilder(Application.class).build().run(args);
    }


//    /**
//     * Класс, обрабатывающий глобальные исключения.
//     */
//    @ControllerAdvice
//    public static class GlobalExceptionHandler {
//
//        /**
//         * Обрабатывает исключения и возвращает ответ с информацией об ошибке.
//         *
//         * @param request   запрос, где произошла ошибка.
//         * @param exception выброшенная ошибка.
//         * @return ответ с информациоей об ошибке.
//         * @see ErrorType
//         */
//        @ExceptionHandler(Exception.class)
//        public final ResponseEntity<ErrorType> internalServerException(
//                final HttpServletRequest request, final Exception exception) {
//            return new ResponseEntity<>(
//                    new ErrorType(request.getRequestURI(), INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
//                    INTERNAL_SERVER_ERROR);
//        }
//
//        /**
//         * Обрабатывает {@link MissingServletRequestParameterException} и возвращает ответ с информацией об ошибке.
//         *
//         * @param request   запрос, где произошла ошибка.
//         * @param exception выброшенная ошибка.
//         * @return ответ с информацией об ошибке.
//         * @see ErrorType
//         */
//        @ExceptionHandler(MissingServletRequestParameterException.class)
//        public final ResponseEntity<ErrorType> missingRequestParameterException(
//                final HttpServletRequest request, final MissingServletRequestParameterException exception) {
//            return new ResponseEntity<>(
//                    new ErrorType(request.getRequestURI(), BAD_REQUEST.value(), exception.getMessage()),
//                    BAD_REQUEST);
//        }
//
//    }

}
