package SpringApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping(value="/Register")
    public String register(){
        return "Register";
    }
}
