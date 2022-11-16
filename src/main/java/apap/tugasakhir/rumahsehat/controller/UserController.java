package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String userMenu(Model model) {
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        return "user-menu";
    }
}
