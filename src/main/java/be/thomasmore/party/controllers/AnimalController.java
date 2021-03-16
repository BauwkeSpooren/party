package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.AnimalRepository;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;


    @GetMapping({"/animaldetails", "/animaldetails/{id}"})
    public String home(Model model, @PathVariable(required = false) Integer id) {

        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            long nrOfVenues = animalRepository.count();
            model.addAttribute("animal", optionalAnimal.get());
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfVenues);
            model.addAttribute("nextId", id < nrOfVenues ? id + 1 : 1);
        }

        return "animaldetails";
    }
}
