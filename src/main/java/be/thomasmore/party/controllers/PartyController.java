package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

@Controller
public class PartyController {
    private Logger logger = LoggerFactory.getLogger(PartyController.class);

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/partydetails", "/partydetails/{id}"})
    public String partyDetails(Model model,
                               @PathVariable(required = false) Integer id,
                               @ModelAttribute("party") Party party) {
        if (id == null) return "partydetails";

        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            long nrOfParties = partyRepository.count();
            boolean priceTest;
            priceTest = optionalParty.get().getPriceInEur() != null && optionalParty.get().getPricePresaleInEur() != null;
            boolean peopleComing;
            peopleComing = optionalParty.get().getAnimals() != null;

            model.addAttribute("partyChosen", optionalParty.get());
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfParties);
            model.addAttribute("nextId", id < nrOfParties ? id + 1 : 1);
            model.addAttribute("priceTest", priceTest);
            model.addAttribute("peopleComing", peopleComing);
        }

        return "partydetails";
    }

    @GetMapping({"/partylist"})
    public String partyList(Model model,
                            @ModelAttribute("party") Party party) {
        logger.info("partylist");
        Iterable<Party> parties = partyRepository.findAll();
        long nrOfParties = partyRepository.count();
        model.addAttribute("parties", parties);
        model.addAttribute("nrOfParties", nrOfParties);
        model.addAttribute("showFilters", false);
        return "partylist";
    }

    @ModelAttribute("party")
    public Party findParty(@PathVariable (required = false) Integer id) {
        logger.info("findparty "+ id);

        if (id != null) {
            Optional<Party> optionalParty = partyRepository.findById(id);
            if(optionalParty.isPresent()) {
                return optionalParty.get();
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }
}
