package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Drink;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DrinkRepository drinkRepository;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/drinklist")
    public String drinkList(Model model,
                            @RequestParam(required = false) Double maxPrijs,
                            @RequestParam(required = false) Boolean alcoholic,
                            @RequestParam(required = false) Boolean light)
    {
        Iterable<Drink> drinks = drinkRepository.findByFilter(maxPrijs,alcoholic,light);
        model.addAttribute("drinks", drinks);
        model.addAttribute("maxPrijs",maxPrijs);
        model.addAttribute("alcoholic",alcoholic);
        model.addAttribute("light",light);

        return "drinklist";
    }
}
