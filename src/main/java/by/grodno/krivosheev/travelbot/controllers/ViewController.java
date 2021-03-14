package by.grodno.krivosheev.travelbot.controllers;

import by.grodno.krivosheev.travelbot.services.CityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ViewController {
    private final CityService cityService;

    public ViewController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("cites", cityService.listCities());
        return "index";
    }

}
