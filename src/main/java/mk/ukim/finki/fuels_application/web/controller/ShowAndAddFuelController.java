package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.exceptions.FuelNotFoundException;
import mk.ukim.finki.fuels_application.service.FuelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/showAndAddFuel")
public class ShowAndAddFuelController {

    private final FuelService fuelService;

    public ShowAndAddFuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping
    public String getShowAndAddFuelPage(@RequestParam(required = false) String error, Model model){

        if(error != null && error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("showAllFuels", this.fuelService.findAll());
        model.addAttribute("title", "Сите бензински пумпи");
        model.addAttribute("bodyContent", "showAllFuels");

        return "master-template";
    }

    @GetMapping("/addNewFuel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewFuel(Model model){

        model.addAttribute("title", "Додади нова бензинска пумпа");
        model.addAttribute("bodyContent", "addNewFuel");

        return "master-template";
    }

    @PostMapping("/add-fuel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addFuel(@RequestParam(required = false) Long id, HttpServletRequest request){

        String fuelName = request.getParameter("fuelName");
        Float fuelLat = Float.parseFloat(request.getParameter("fuelLat"));
        Float fuelLong = Float.parseFloat(request.getParameter("fuelLong"));
        String imageUrl = request.getParameter("imageUrl");
        String pageLink = request.getParameter("pageLink");

        if(id != null){
            this.fuelService.editFuel(id, fuelName, fuelLat, fuelLong, imageUrl, pageLink);
        }
        else {
            this.fuelService.addNewFuel(fuelName, fuelLat, fuelLong, imageUrl, pageLink);
        }

        return "redirect:/showAndAddFuel";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFuel(@PathVariable Long id){

        this.fuelService.deleteById(id);
        return "redirect:/showAndAddFuel";
    }

    @GetMapping("/edit-fuel/{id}")
    public String editFuel(@PathVariable Long id, Model model){

        if(this.fuelService.findById(id).isPresent()){
            Fuel fuel = this.fuelService.findById(id).get();
            model.addAttribute("selectedFuel", fuel);
            model.addAttribute("title", "Додади нова бензинска пумпа");
            model.addAttribute("bodyContent", "addNewFuel");

            return "master-template";
        }
        return "redirect:/showAndAddFuel?error="+ new FuelNotFoundException(id).getMessage();
    }
}
