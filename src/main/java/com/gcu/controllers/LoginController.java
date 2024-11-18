package com.gcu.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.User;
import com.gcu.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("login")
public class LoginController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login(Model model,RedirectAttributes redirect,HttpSession httpSession) {
        User currentUser = (User)httpSession.getAttribute("currentUser");
        if(Objects.nonNull(currentUser)){
            model.addAttribute("user", currentUser);
            return "redirect:/dashboard";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/")
    public String login(User user,RedirectAttributes redirect,HttpSession httpSession) {
        User validUser = userService.validatUser(user.getEmail(), user.getPassword());

        if(Objects.nonNull(validUser)){
            httpSession.setAttribute("currentUser", validUser);
            return "redirect:/dashboard";
        }
        redirect.addFlashAttribute("error","Invalid email or password, please try again");
        return "redirect:/login";
    }
   
}