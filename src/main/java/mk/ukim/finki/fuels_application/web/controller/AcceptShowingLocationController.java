package mk.ukim.finki.fuels_application.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/acceptShowingLocation")
public class AcceptShowingLocationController {

    public AcceptShowingLocationController(){}

    @GetMapping
    public String getAcceptPage(Model model){

        return "acceptShowingLocation";
    }

    @PostMapping
    public String postAcceptShowingLocation(@RequestParam String accept){

        if(accept.equals("YES")){
            return "redirect:/home";
        }

        return "redirect:/logout";
    }

}
