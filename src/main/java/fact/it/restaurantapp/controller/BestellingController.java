package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.repository.BestellingRepository;
import org.springframework.stereotype.Controller;

@Controller
public class BestellingController {
    private BestellingRepository bestellingRepository;

    public BestellingController(BestellingRepository bestellingRepository) {
        this.bestellingRepository = bestellingRepository;
    }


}
