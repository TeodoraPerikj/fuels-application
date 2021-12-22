package mk.ukim.finki.fuels_application.web.controller;

import mk.ukim.finki.fuels_application.model.exceptions.StreetByNameNotFoundException;
import mk.ukim.finki.fuels_application.service.StreetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StreetService streetService;

    public HomeController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        model.addAttribute("streets", this.streetService.findAll());

        return "home";
    }

    @PostMapping
    public String enterLocation(HttpServletRequest request){

        String location = request.getParameter("location");

        try{
            this.streetService.findByName(location);
            request.getSession().setAttribute("location",location);
            return "redirect:/showFuels";
        }catch (StreetByNameNotFoundException exception){
            return "redirect:/home?error="+exception.getMessage();
        }


    }

}
