package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.Bestelling;
import fact.it.restaurantapp.repository.BestellingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BestellingController {
    private BestellingRepository bestellingRepository;

    public BestellingController(BestellingRepository bestellingRepository) {
        this.bestellingRepository = bestellingRepository;
    }

    @RequestMapping(value = "/bestellingen", method = RequestMethod.GET)
    public String bestellingenWeergeven(HttpServletRequest request, Model model) {
        List<Bestelling> bestellings = bestellingRepository.findAll();
        model.addAttribute("bestellingList", bestellings);
        return "bestelling/index";
    }

}
