package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping("/partyedit/{id}")
    public String partyedit(Model model, @PathVariable(required = false) Integer id) {
        logger.info("partyEdit is uitgevoerd?");

        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);

        Optional<Party> optionalParty = partyRepository.findById(id);

        boolean priceTest;
        priceTest = optionalParty.get().getPriceInEur() != null && optionalParty.get().getPricePresaleInEur() != null;

        model.addAttribute("party", optionalParty.get());
        model.addAttribute("priceTest", priceTest);

        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyeditpost(Model model, @PathVariable(required = false) Integer id,
                                @ModelAttribute("party") Party party) {
        logger.info("partyEdit post " + id);

        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id) {
        logger.info("findparty " + id);

        if (id == null)  return new Party();

        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            return optionalParty.get();
        }
        return null;
    }


    @GetMapping("/partynew")
    public String partynew(Model model) {

        logger.info("party new uitvoeren");

        Party party = new Party();
        model.addAttribute("party", party);

        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);

        return "admin/partynew";
    }

    @PostMapping("/partynew")
    public String partynewpost(Model model,
                               @ModelAttribute("party") Party party,
                               @RequestParam int venueId) {

        party.setVenue(new Venue(venueId));
        Party newParty = partyRepository.save(party);
        return "redirect:/partynew/" + newParty.getId();
    }

}
