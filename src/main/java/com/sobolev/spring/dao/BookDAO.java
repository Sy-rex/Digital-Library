package com.sobolev.spring.dao;

import com.sobolev.spring.dao.BookRowMapper;
import com.sobolev.spring.models.Book;
import com.sobolev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Optional<Book> show(String title){
        return jdbcTemplate.query("SELECT * FROM Book WHERE title=?",new BookRowMapper(),title).stream().findAny();
    }

    public Book findById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BookRowMapper(),id).stream().findAny().orElse(null);
    }

    public int countTitle(int id, String title) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(title) FROM Book WHERE title = ? AND id != ?",
                new Object[]{title, id},
                Integer.class
        );
    }

    public String findConsumer(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT fio FROM Person JOIN Book ON Person.id = Book.person_id WHERE Book.id=?",
                new Object[]{id},
                String.class
        );
    }



    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(title,author,year_of_foundation) VALUES(?,?,?)", book.getTitle(), book.getAuthor(), book.getYearOfFoundation());
    }

    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_foundation=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getYearOfFoundation(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }
}
