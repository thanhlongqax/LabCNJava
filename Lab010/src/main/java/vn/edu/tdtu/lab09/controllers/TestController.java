package vn.edu.tdtu.lab09.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/admin")
    public String getAdminContent(){
        return "admin content";
    }

    @GetMapping("/user")
    public String getUserContent(){
        return "user content";
    }
}
