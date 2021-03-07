package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller

public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";

        Optional<Venue> optionalVenue = venueRepository.findById(id);
        if (optionalVenue.isPresent()) {
            long nrOfVenues = venueRepository.count();
            model.addAttribute("venue", optionalVenue.get());
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfVenues);
            model.addAttribute("nextId", id < nrOfVenues ? id + 1 : 1);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model, @RequestParam(required = false) Integer capacityAsking) {
        Iterable<Venue> venues;
        if (capacityAsking == null) {
            venues = venueRepository.findAll();
        }
        else {
            venues = venueRepository.findByCapacityGreaterThan(capacityAsking);
        }
        model.addAttribute("venues", venues);
        long amountVenue = venueRepository.count();
        model.addAttribute("amount", amountVenue);
        model.addAttribute("showFilters", false);
        model.addAttribute("capacity", capacityAsking);
        return "venuelist";
    }

    @GetMapping("/venuelist/filter")
    public String venueListFilter(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("showFilters", true);
        long amountVenue = venueRepository.count();
        model.addAttribute("amount", amountVenue);
        model.addAttribute("venues", venues);
        return "venuelist";
    }
}
