package se.ecutb.springmvcexercisesandthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ArrayList;

@Controller
public class Home {

    List<String> inputList = new ArrayList<>();

    @GetMapping("/index")
    public String index (){
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


    @PostMapping("/contact")
    public String contact(@RequestParam String input){
        if (!input.isEmpty()) {
            inputList.add(input);
            return "redirect:/contact/inputs";
        }
        return null;
    }

    @GetMapping("contact/inputs")
    public String contactList (Model model){
        model.addAttribute("contacts", inputList);
        return "inputs";
    }

    @GetMapping("/about")
    public String about (){
        return "about";
    }
}
