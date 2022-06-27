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
    public static final String STRING = "/";
    public static final String TITLE = "title";
    public static final String ГЛАВНАЯ_СТРАНИЦА = "Главная страница";
    public static final String HOME = "home";
    public static final String HORSE = "/horse";
    public static final String HORSES = "horses";
    public static final String HORSES_MAIN = "horses-main";
    public static final String HORSE_ADD = "/horse/add";
    public static final String HORSE_ADD1 = "horse-add";
    public static final String STRING1 = "redirect:/horse";
    public static final String HORSE_ID = "/horse/{id}";
    public static final String ID = "id";
    public static final String HORSE_DETAILS = "horse-details";
    public static final String HORSE_ID_EDIT = "/horse/{id}/edit";
    public static final String HORSE_EDIT = "horse-edit";
    public static final String REDIRECT_HORSE = "redirect:/horse";
    public static final String HORSE_ID_REMOVE = "/horse/{id}/remove";
    @Autowired
    private HorseService horseService;

    @GetMapping(STRING)//главная страница
    public String home(Model model) {
        model.addAttribute(TITLE, ГЛАВНАЯ_СТРАНИЦА);
        return HOME;
    }

    @RequestMapping(HORSE)//все записи
    public String horsesMain(Model model) {
        model.addAttribute(HORSES, horseService.findAll());
        return HORSES_MAIN;
    }

    @RequestMapping(HORSE_ADD)//добавление
    public String blogAdd(Model model) {
        return HORSE_ADD1;
    }

    @PostMapping(HORSE_ADD)//добавление (получение из формы)
    public String blogPostAdd(@RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.createHorse(type, Integer.parseInt(age), Integer.parseInt(price));
        return STRING1;
    }

    @GetMapping(HORSE_ID)//вывод одной записи
    public String blogDetails(@PathVariable(value = ID) Integer id, Model model) {
        ArrayList<Horse> res = new ArrayList<>();
        horseService.findHorseById(id).ifPresent(res::add);//из класса Optional переводим в класс ArrayList
        model.addAttribute(HORSES, res);
        return HORSE_DETAILS;
    }

    @GetMapping(HORSE_ID_EDIT)//edit
    public String blogEdit(@PathVariable(value = ID) Integer id, Model model) {
        ArrayList<Horse> res = new ArrayList<>();
        horseService.findHorseById(id).ifPresent(res::add);//из класса Optional переводим в класс ArrayList
        model.addAttribute(HORSES, res);
        return HORSE_EDIT;
    }

    @PostMapping(HORSE_ID_EDIT)//edit (получение из формы)
    public String blogPostUpdate(@PathVariable(value = ID) Integer id, @RequestParam String type, @RequestParam String age, @RequestParam String price, Model model) {
        horseService.updateHorse(id, type, Integer.parseInt(age), Integer.parseInt(price));
        return REDIRECT_HORSE;
    }

    @PostMapping(HORSE_ID_REMOVE)//delete
    public String blogPostDelete(@PathVariable(value = ID) Integer id, Model model) {
        horseService.deleteHorse(id);
        return REDIRECT_HORSE;
    }
}
