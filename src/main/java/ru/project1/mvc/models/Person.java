package ru.project1.mvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class Person {

    private int id;

    @NotEmpty(message = "ФИО должно быть заполнено")
    private String fullName;

    @Min(value = 1, message = "Год рождения должен быть больше 0")
    private int yearBirth;

    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int yearBirth, List<Book> books) {
        this.fullName = fullName;
        this.yearBirth = yearBirth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
