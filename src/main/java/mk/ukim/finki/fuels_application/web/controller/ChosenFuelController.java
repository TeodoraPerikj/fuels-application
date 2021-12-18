package mk.ukim.finki.fuels_application.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chosen-fuel")
public class ChosenFuelController {

    public ChosenFuelController() {

    }

    @GetMapping
    public String showFuel(){

        return "showFuel";
    }
}
