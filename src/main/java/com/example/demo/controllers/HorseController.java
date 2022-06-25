package com.example.demo.controllers;

import com.example.demo.models.Horse;
import com.example.demo.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HorseController {
    @Autowired
    private HorseService horseService;

    @GetMapping("/")//главная страница
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @RequestMapping("/horse")//все записи
    public String horsesMain(Model model) {
        model.addAttribute("horses", horseService.findAll());
        return "horses-main";
    }

    @RequestMapping("/horse/add")//добавление
    public String blogAdd(Model model) {
        return "horse-add";
    }

    @PostMapping("/horse/add")//добавление (получение из формы)
    public String blogPostAdd(@RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.createHorse(type, Integer.parseInt(age), Integer.parseInt(price));
        return "redirect:/horse";
    }

    @GetMapping("/horse/{id}")//вывод одной записи
    public String blogDetails(@PathVariable(value = "id") Integer id, Model model) {
        ArrayList<Horse> res = new ArrayList<>();
        horseService.findHorseById(id).ifPresent(res::add);//из класса Optional переводим в класс ArrayList
        model.addAttribute("horses", res);
        return "horse-details";
    }

    @GetMapping("/horse/{id}/edit")//edit
    public String blogEdit(@PathVariable(value = "id") Integer id, Model model) {
        ArrayList<Horse> res = new ArrayList<>();
        horseService.findHorseById(id).ifPresent(res::add);//из класса Optional переводим в класс ArrayList
        model.addAttribute("horses", res);
        return "horse-edit";
    }

    @PostMapping("/horse/{id}/edit")//edit (получение из формы)
    public String blogPostUpdate(@PathVariable(value = "id") Integer id, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.updateHorse(id, type, Integer.parseInt(age), Integer.parseInt(price));
        return "redirect:/horse";
    }

    @PostMapping("/horse/{id}/remove")//delete
    public String blogPostDelete(@PathVariable(value = "id") Integer id, Model model) {
        horseService.deleteHorse(id);
        return "redirect:/horse";
    }
}
