package com.sobolev.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.lang.Nullable;

public class Book {
    private int id;
    private String title;

    @Pattern(regexp = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$", message = "Автор должен быть в формате: Имя Фамилия")
    private String author;

    private int personId;

    @Min(value = 1, message = "Год написания книги не может быть меньше 1")
    @Max(value = 2024,message = "Год написания книги не может быть больше 2024")
    private int yearOfFoundation;

    public Book() {}

    public Book(int id, String title, String author, int personId, int yearOfFoundation) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfFoundation = yearOfFoundation;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
