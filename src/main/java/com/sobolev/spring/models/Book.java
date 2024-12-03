package com.sobolev.spring.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private int yearOfFoundation;
    private int idPerson;

    public Book(int id, String title, String author, int yearOfFoundation, int idPerson) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfFoundation = yearOfFoundation;
        this.idPerson = idPerson;
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

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }
}
