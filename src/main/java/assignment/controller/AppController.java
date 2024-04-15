package assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AppController {
    @GetMapping("")
    public String home(Principal principal){
        if (principal == null){
            return "login";
        }
        return "redirect:/course";
    }
}
