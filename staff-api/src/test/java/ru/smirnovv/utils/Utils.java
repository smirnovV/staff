package ru.smirnovv.utils;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Утилитный класс для тестов
 */
public class Utils {

    private Utils() {
    }

    /**
     * Сохранить УИД в {@link AbstractPersistable}
     *
     * @param entity сущность
     * @param value  значение УИД
     * @param <S>    класс УИД-а
     * @param <T>    класс сущности
     * @return сущность
     */
    public static <S extends Serializable, T extends AbstractPersistable<S>> T setId(T entity, S value) {
        try {
            Method method = AbstractPersistable.class.getDeclaredMethod("setId", Serializable.class);
            method.setAccessible(true);
            method.invoke(entity, value);
            return entity;
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
