package be.thomasmore.party.controllers;

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

    private final String appName = "WOOOO";
    private final String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private final String paydate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private final String[] venueNames = {"De Loods", "De Club", "De Hanger", "Zapoi", "Kuub", "Cuba Libre"};

    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("date",date);
        model.addAttribute("paydate",paydate);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "pay";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{index}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer index) {
        if (index!=null && index>=0 && index<venueNames.length ) {
            model.addAttribute("venueName", venueNames[index]);
            model.addAttribute("prevIndex", index>0 ? index-1 : venueNames.length-1);
            model.addAttribute("nextIndex", index<venueNames.length-1 ? index+1 : 0);
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        model.addAttribute("venueNames", venueNames);
        return "venuelist";
    }
}
