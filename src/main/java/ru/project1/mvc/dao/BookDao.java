package ru.project1.mvc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.project1.mvc.models.Book;
import ru.project1.mvc.models.BookMapper;
import ru.project1.mvc.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT book.id, book.name, book.author, book.year, book.person_id, person.fullname, person.yearbirth FROM book LEFT JOIN person ON book.person_id = person.id WHERE book.id = ?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void assign(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, id);
    }

    public void free(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", null, id);
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

}
