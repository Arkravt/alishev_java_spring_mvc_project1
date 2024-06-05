package ru.project1.mvc.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.project1.mvc.models.Book;

public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;



    }
}
