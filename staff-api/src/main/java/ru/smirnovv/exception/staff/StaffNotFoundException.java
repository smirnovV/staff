package ru.smirnovv.exception.staff;

import ru.smirnovv.exception.ApplicationException;

public class StaffNotFoundException extends ApplicationException {

    public StaffNotFoundException(String message) {
        super(message);
    }

}
