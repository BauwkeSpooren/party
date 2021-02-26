package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
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

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        model.addAttribute("venueName", venueRepository.findById(id).get());
        if (id!=null && id>0 && id<venueRepository.count() ) {
            model.addAttribute("prevIndex", id>0 ? id-1 : venueRepository.count()-1);
            model.addAttribute("nextIndex", id<venueRepository.count()-1 ? id+1 : 0);
        }
        return "venuedetails";
    }

    @GetMapping({"/artistsdetails", "/artistsdetails/{id}"})
    public String artistsDetails(Model model,
                               @PathVariable(required = false)  Integer id) {
        model.addAttribute("artistName", artistRepository.findById(id).get());
        if (id!=null && id>0 && id<artistRepository.count() ) {
            model.addAttribute("prevIndex", id>0 ? id-1 : artistRepository.count()-1);
            model.addAttribute("nextIndex", id<artistRepository.count()-1 ? id+1 : 0);
        }
        return "artistsdetails";
    }

    @GetMapping({"/venuelist","/venuelist/outdoor","/venuelist/{something}"})
    public String venuelist(Model model, @PathVariable (required = false) String something) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/{filter}")
    public String venuelistOutdoor(Model model, @PathVariable String filter) {
        if (filter.equals("yes")) {
            Iterable<Venue> venues = venueRepository.Outdoor(true);
            model.addAttribute("venues", venues);
        }
        else if (filter.equals("no")) {
            Iterable<Venue> venues = venueRepository.Outdoor(false);
            model.addAttribute("venues", venues);
        }
        else {
            Iterable<Venue> venues = venueRepository.findAll();
            model.addAttribute("venues", venues);
        }
        return "venuelist";
    }

    @GetMapping("/artistlist")
    public String artistlist(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }
}
