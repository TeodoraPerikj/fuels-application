package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.User;
import mk.ukim.finki.fuels_application.service.AuthService;
import org.springframework.stereotype.Controller;
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
    public String getChangeProfilePage(){

        return "changeProfile";
    }

    @PostMapping
    public String postChangedProfile(HttpServletRequest request){

        String username = request.getParameter("changeProfileUsername");
        String password = request.getParameter("changeProfilePassword");
        String nameProfile = request.getParameter("nameProfile");
        String surnameProfile = request.getParameter("surnameProfile");

        User user = (User) request.getSession().getAttribute("user");

        User newUser = this.authService.editProfile(username, password, nameProfile, surnameProfile);
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());

        return "redirect:/home";
    }
}
