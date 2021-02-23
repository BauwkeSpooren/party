package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller

public class HomeController {

    private final Venue[] venues = {
            new Venue("De Loods","https://www.facebook.com/boesjkammeree/", 5,true, false, false, true,"france",5000),
            new Venue("De Club","https://www.youtube.com/watch?v=4rlc2EgSdbU&list=PL0bHKk6wuUGLWGipKSf0dFrpuzDitERqD", 5,true, true, false, false,"dsfdsfds",555555),
            new Venue("De Hanger","https://www.facebook.com/boesjkammeree/", 5,false, true, false, true,"iolouiouio",2555)
    };

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{index}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer index) {
        if (index!=null && index>=0 && index<venues.length ) {
            model.addAttribute("venueName", venues[index]);
            model.addAttribute("prevIndex", index>0 ? index-1 : venues.length-1);
            model.addAttribute("nextIndex", index<venues.length-1 ? index+1 : 0);
            model.addAttribute("venues", venues);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        model.addAttribute("venues", venues);
        return "venuelist";
    }
}
