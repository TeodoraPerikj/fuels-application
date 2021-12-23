package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.User;
import mk.ukim.finki.fuels_application.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/changeProfile")
public class ChangeProfileController {

    private final AuthService authService;

    public ChangeProfileController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getChangeProfilePage(Authentication authentication, Model model){

        User user = (User) authentication.getPrincipal();

        model.addAttribute("user", user);

        return "changeProfile";
    }

    @PostMapping
    public String postChangedProfile(HttpServletRequest request, Authentication authentication){

        String nameProfile = request.getParameter("nameProfile");
        String surnameProfile = request.getParameter("surnameProfile");

        User user = (User) authentication.getPrincipal();

        User newUser = this.authService.editProfile(user.getUsername(), nameProfile, surnameProfile);
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());

        return "redirect:/home";
    }
}
