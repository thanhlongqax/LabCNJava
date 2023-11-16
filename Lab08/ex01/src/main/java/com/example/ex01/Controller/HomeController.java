package com.example.ex01.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @PostMapping("/contact")
    public String postContact(
            @RequestParam("fullName") String fullName,
            @RequestParam("age") String age,
            @RequestParam("address") String address,
            Model model
    ){
        model.addAttribute("fullName", fullName);
        model.addAttribute("age", age);
        model.addAttribute("address", address);
        return "contact-response";
    }
    @RequestMapping("/error")
    public String errorPage(){
        return "errorPage";
    }
}
