package com.sobolev.spring.dao;

import com.sobolev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id).stream().findFirst().orElse(null);
    }

    public Optional<Person> show(String fio){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?", new BeanPropertyRowMapper<>(Person.class), fio)
                .stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(fio, year_of_birth) VALUES(?,?)", person.getFio(), person.getYearOfBirth());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE Person SET fio=?, year_of_birth=? WHERE id=?", person.getFio(), person.getYearOfBirth(), id);
    }
}
