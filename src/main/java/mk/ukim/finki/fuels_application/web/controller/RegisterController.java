package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.Role;
import mk.ukim.finki.fuels_application.model.exceptions.*;
import mk.ukim.finki.fuels_application.service.AuthService;
import mk.ukim.finki.fuels_application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname) {

        try{
            Role role;
            if(username.equals("teodora.perikj") || username.equals("iva.ugrinoska")
                    || username.equals("sara.paunkoska")){
                role = Role.ROLE_ADMIN;
            } else {
                role = Role.ROLE_USER;
            }
            this.userService.register(username, password, repeatedPassword, name, surname, role);

            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException
                | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
