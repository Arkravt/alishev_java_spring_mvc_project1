package ru.project1.mvc.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));

        if (rs.getInt("person_id") > 0) {
            Person person = new Person();
            person.setId(rs.getInt("person_id"));
            person.setFullName(rs.getString("fullName"));
            person.setYearBirth(rs.getInt("yearbirth"));
            //book.setPerson(person);
        }

        return book;
    }
}
