package ru.smirnovv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Приложения для управления списком организации и сотрудниками
 */
@SpringBootApplication
public class Application {

    /**
     * Точка входа.
     *
     * @param args программные аргументы.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
