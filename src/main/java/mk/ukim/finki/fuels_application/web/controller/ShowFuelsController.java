package mk.ukim.finki.fuels_application.web.controller;


import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
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
    public String showFuels(Model model, HttpServletRequest request){

        String name = (String) request.getSession().getAttribute("location");

        Street street = this.streetService.findByName(name).get();


//        List<Fuel> fuels = this.fuelService.findAll();
//
//        Float razlikaLat = (float)1000;
//        Float razlikaLong = (float)1000;
//        Long fuelid = null;
//
//        for (Fuel fuel:fuels) {
//            Float fuelLat = fuel.getLatitude();
//            Float fuelLong = fuel.getLongitude();
//
//            if(abs(latitude - fuelLat)<razlikaLat && abs(longitude-fuelLong)<razlikaLong){
//                razlikaLat = abs(latitude - fuelLat);
//                razlikaLong = abs(longitude-fuelLong);
//                fuelid=fuel.getId();
//            }
//
//        }

        List<Fuel> fuels = this.fuelService.findFirstTwo(street);

        List<Double> distances = this.fuelService.findDistances(fuels, street);

        List<String> times = this.fuelService.findTimes(distances);

        Fuel firstFuel = fuels.get(0);
        Fuel secondFuel = fuels.get(1);

        Double firstDist = distances.get(0);
        Double secondDist = distances.get(1);

        String firstTime = times.get(0);
        String secondTime = times.get(1);

        model.addAttribute("firstFuel", firstFuel);
        model.addAttribute("secondFuel", secondFuel);
        model.addAttribute("firstDistance", firstDist);
        model.addAttribute("secondDistance", secondDist);
        model.addAttribute("firstTime", firstTime);
        model.addAttribute("secondTime", secondTime);

        return "showFuels";
    }

    @PostMapping
    public String chosenFuel(HttpServletRequest request){

        Optional<Fuel> fuel = this.fuelService.findByName(request.getParameter("chosenFuel"));
        request.getSession().setAttribute("finalFuel",fuel);
        return "redirect:/chosen-fuel";
    }

}
