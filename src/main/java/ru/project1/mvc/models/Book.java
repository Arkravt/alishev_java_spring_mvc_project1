package ru.project1.mvc.models;

public class Book {

    private int id;
    private String name;
    private String Author;
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        Author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
