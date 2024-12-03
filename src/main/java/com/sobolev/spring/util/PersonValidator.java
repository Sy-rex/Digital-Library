package com.sobolev.spring.util;

import com.sobolev.spring.dao.PersonDAO;
import com.sobolev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    /**
     * Основная логика валидации.
     * @param o Объект, который нужно проверить.
     * @param errors Объект для хранения ошибок валидации.
     */
    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.show(person.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "Такой человек уже есть в системе");
        }
    }
}
