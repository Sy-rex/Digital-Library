package com.sobolev.spring.dao;

import com.sobolev.spring.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        Object personIdObj = rs.getObject("person_id");
        if (personIdObj != null) {
            book.setPersonId((int) personIdObj);
        } else {
            book.setPersonId(0);
        }

        book.setYearOfFoundation(rs.getInt("year_of_foundation"));

        return book;
    }
}
