package psu.edu.paradiseClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    //add request mapping
    @GetMapping("/access-denied")
    public String showAccessDeneied() {
        return "access-denied";
    }
    
}