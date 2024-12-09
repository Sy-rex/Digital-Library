package com.sobolev.spring.controllers;


import com.sobolev.spring.dao.BookDAO;
import com.sobolev.spring.dao.PersonDAO;
import com.sobolev.spring.models.Book;
import com.sobolev.spring.models.Person;
import com.sobolev.spring.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDao;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDao, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookDao.findById(id);
        System.out.println(book.getPersonId());
        model.addAttribute("book", bookDao.findById(id));
        model.addAttribute("people", personDAO.index());
        //model.addAttribute("pers", bookDao.findConsumer(id));
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String selectConsumer(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        return null;
    }
}
