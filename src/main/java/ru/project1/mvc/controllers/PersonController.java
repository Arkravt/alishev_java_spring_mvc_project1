package ru.project1.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project1.mvc.dao.BookDao;
import ru.project1.mvc.dao.PersonDao;
import ru.project1.mvc.models.Person;
import ru.project1.mvc.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PersonController {

    private PersonDao personDao;
    private PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String people(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.get(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult
    ) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDao.update(person, id);
//        return "redirect:/people/{" + id + "}";
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.get(id));
        model.addAttribute("books", personDao.getPersonsBooks(id));
        return "people/show";
    }

}
