package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.Fuel;
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

        return "showAllFuels";
    }

    @GetMapping("/addNewFuel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewFuel(){

        return "addNewFuel";
    }

    @PostMapping("/add-fuel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addFuel(@RequestParam(required = false) Long id, HttpServletRequest request){

        String fuelName = request.getParameter("fuelName");
        Float fuelLat = Float.parseFloat(request.getParameter("fuelLat"));
        Float fuelLong = Float.parseFloat(request.getParameter("fuelLong"));

        if(id != null){
            this.fuelService.editFuel(id, fuelName, fuelLat, fuelLong);
        }
        else {
            this.fuelService.addNewFuel(fuelName, fuelLat, fuelLong);
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
            return "addNewFuel";
        }
        return "redirect:/showAndAddFuel?error=FuelNotFoundException";
    }
}
