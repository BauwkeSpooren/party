package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping({"/venuedetailsbyid", "/venuedetailsbyid/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        model.addAttribute("venueName", venueRepository.findById(id).get());
        if (id!=null && id>=0 && id<venueRepository.count() ) {
            model.addAttribute("prevIndex", id>0 ? id-1 : venueRepository.count()-1);
            model.addAttribute("nextIndex", id<venueRepository.count()-1 ? id+1 : 0);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }
}
