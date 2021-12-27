package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.service.FuelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showMap")
public class MapController {
    private final FuelService fuelService;

    public MapController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping
    public String getPage()
    {
        return "showMap";
    }
}
