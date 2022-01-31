package mk.ukim.finki.fuels_application.web.controller;


import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.FuelByNameNotFoundException;
import mk.ukim.finki.fuels_application.service.FuelService;
import mk.ukim.finki.fuels_application.service.StreetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

@Controller
@RequestMapping("/showFuels")
public class ShowFuelsController {

    private final FuelService fuelService;
    private final StreetService streetService;

    public ShowFuelsController(FuelService fuelService, StreetService streetService) {
        this.fuelService = fuelService;
        this.streetService = streetService;
    }

    @GetMapping
    public String showFuels(@RequestParam(required = false) String error, Model model, HttpServletRequest request){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", "true");
            model.addAttribute("error", error);
        }

        String name = (String) request.getSession().getAttribute("location");

        Street street = null;

        if(this.streetService.findByName(name).isPresent())
            street = this.streetService.findByName(name).get();

        List<Fuel> fuels = this.fuelService.findFirstTwo(street);

        List<Double> distances = this.fuelService.findDistances(fuels, street);

        List<String> times = this.fuelService.findTimes(distances);

        model.addAttribute("fuels", fuels);
        model.addAttribute("distances", distances);
        model.addAttribute("times", times);
        model.addAttribute("title", "Прикажи бензински пумпи");
        model.addAttribute("bodyContent", "showFuels");

        return "master-template";
    }

    @PostMapping
    public String chosenFuel(HttpServletRequest request){

        Optional<Fuel> fuel;
        try {
            fuel = this.fuelService.findByName(request.getParameter("chosenFuel"));
            Fuel foundFuel = null;
            if(fuel.isPresent()){
                foundFuel = fuel.get();
            }

            request.getSession().setAttribute("finalFuel", foundFuel);
            return "redirect:/showMap";
        }catch(FuelByNameNotFoundException exception){
            return "redirect:/showFuels?error="+exception.getMessage();
        }
    }

}
