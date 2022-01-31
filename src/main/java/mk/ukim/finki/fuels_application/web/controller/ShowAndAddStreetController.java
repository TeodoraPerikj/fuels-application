package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.StreetNotFoundException;
import mk.ukim.finki.fuels_application.service.StreetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/showAndAddStreet")
public class ShowAndAddStreetController {

    private final StreetService streetService;

    public ShowAndAddStreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public String getShowAndAddStreetPage(@RequestParam(required = false) String error, Model model){

        if(error != null && error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("showAllStreets", this.streetService.findAll());
        model.addAttribute("title", "Сите улици");
        model.addAttribute("bodyContent", "showAllStreets");

        return "master-template";
    }

    @GetMapping("/addNewStreet")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewStreet(Model model){

        model.addAttribute("title", "Додади нова улица");
        model.addAttribute("bodyContent", "addNewStreet");

        return "master-template";
    }

    @PostMapping("/add-street")
    public String addStreet(@RequestParam(required = false) Long id, HttpServletRequest request){

        String streetName = request.getParameter("streetName");
        Float streetLat = Float.parseFloat(request.getParameter("streetLat"));
        Float streetLong = Float.parseFloat(request.getParameter("streetLong"));

        if(id != null){
            this.streetService.editStreet(id, streetName, streetLat, streetLong);
        }
        else {
            this.streetService.addNewStreet(streetName, streetLat, streetLong);
        }

        return "redirect:/showAndAddStreet";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStreet(@PathVariable Long id){

        this.streetService.deleteById(id);
        return "redirect:/showAndAddStreet";
    }

    @GetMapping("/edit-street/{id}")
    public String editStreet(@PathVariable Long id, Model model){

        if(this.streetService.findById(id).isPresent()){
            Street street = this.streetService.findById(id).get();
            model.addAttribute("selectedStreet", street);
            model.addAttribute("title", "Додади нова улица");
            model.addAttribute("bodyContent", "addNewStreet");

            return "master-template";
        }
        return "redirect:/showAndAddStreet?error="+ new StreetNotFoundException(id).getMessage();
    }

}
