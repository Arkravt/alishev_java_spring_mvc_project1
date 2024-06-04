package ru.project1.mvc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.project1.mvc.dao.PersonDao;
import ru.project1.mvc.models.Person;

@Component
public class PersonValidator implements Validator {

    private PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if(personDao.get(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName","","Это ФИО уже существует");
        }

    }
}
