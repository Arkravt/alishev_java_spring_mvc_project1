package ru.project1.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project1.mvc.dao.BookDao;
import ru.project1.mvc.dao.PersonDao;
import ru.project1.mvc.models.Book;
import ru.project1.mvc.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String books(Model model) {
        model.addAttribute("books", bookDao.index());
        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        bookDao.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookDao.get(id));
        model.addAttribute("people", personDao.index());

        Optional<Person> person = personDao.gePersonByBookId(id);

        if (person.isPresent()) {
            model.addAttribute(person.get());
        } else {
            model.addAttribute(new Person());
        }

        return "book/show";
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDao.assign(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("{id}/free")
    public String free(@PathVariable("id") int id) {
        bookDao.free(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.get(id));
        return "book/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/edit";
        }

        bookDao.update(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

}
