package ru.smirnovv.utils;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Утилитный класс для тестирования
 */
public class TestUtils {

    private TestUtils() {
    }

    public static <T extends Serializable> void setPrivateId(AbstractPersistable<T> persistable,
                                                     T value) throws NoSuchFieldException, IllegalAccessException {
        Field field = persistable.getClass()
                              .getSuperclass()
                              .getDeclaredField("id");
        field.setAccessible(true);
        field.set(persistable, value);
    }

}
