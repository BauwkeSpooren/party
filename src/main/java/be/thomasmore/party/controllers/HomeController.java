package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller

public class HomeController {

    private final int mySpecialNumber = 729;
    private final String appName = "WOOOO";
    private final String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private final String paydate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("date",date);
        model.addAttribute("paydate",paydate);
        return "pay";
    }

    @GetMapping("/venuedetails/{venueName}")
    public String venuedetails(Model model, @PathVariable String venueName) {
        model.addAttribute("venueName",venueName);
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        return "venuelist";
    }
}
