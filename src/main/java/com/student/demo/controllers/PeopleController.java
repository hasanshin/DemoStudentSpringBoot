package com.student.demo.controllers;

import com.student.demo.models.Person;
import com.student.demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/home")
    public String index(Model model) {
        Iterable<Person> personIterable = peopleRepository.findAll();
        model.addAttribute("people", personIterable);
        return "home";
    }

    @GetMapping("/home/new")
    public String newPerson (Model model) {
      return "new";
    }

    @PostMapping("/home/new")
    public String newPersonAdd(@RequestParam String name,
                               @RequestParam int age,
                               @RequestParam String email,
                               Model model){
        Person person = new Person(name, age, email);
        peopleRepository.save(person);

        return "redirect:/home";
    }

    @GetMapping("show/{id}")
    public String personShow(@PathVariable(value = "id")
                                         long id,
                             Model model){
        Optional<Person> personCurrent = peopleRepository.findById(id);
        ArrayList<Person> res = new ArrayList<>();
        personCurrent.ifPresent(res::add);
        model.addAttribute("personCurrent", res);

        return "show";
    }

}
