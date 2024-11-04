package com.gcu.controllers;
import java.util.Objects;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {


    @GetMapping("/dashboard")
    public String showDashboard(Model model,RedirectAttributes redirect,HttpSession httpSession) {
       User currentUser = (User)httpSession.getAttribute("currentUser");
        if(Objects.isNull(currentUser)){
            redirect.addAttribute("error", "You are not logged in, please sign in");
            return "redirect:/dashboard";
        }
        model.addAttribute("user", currentUser);
        return "dashboard";
    }
}
