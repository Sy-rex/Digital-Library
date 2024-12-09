package com.sobolev.spring.util;

import com.sobolev.spring.dao.BookDAO;
import com.sobolev.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator{
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    /**
     * Основная логика валидации.
     * @param o Объект, который нужно проверить.
     * @param errors Объект для хранения ошибок валидации.
     */
    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (bookDAO.show(book.getTitle()).isPresent() && bookDAO.countTitle(book.getId(), book.getTitle()) != 0 ){
            errors.rejectValue("title", "", "Такое произведение уже есть в системе");
        }
    }
}
