package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.*;
import fact.it.restaurantapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class StartController {

    private final PersoneelRepository personeelRepository;
    private final GerechtRepository gerechtRepository;
    private final BestellingRepository bestellingRepository;
    private final BesteldItemRepository besteldItemRepository;
    private final TafelRepository tafelRepository;

    public StartController(PersoneelRepository personeelRepository, GerechtRepository gerechtRepository, BestellingRepository bestellingRepository, BesteldItemRepository besteldItemRepository, TafelRepository tafelRepository) {
        this.personeelRepository = personeelRepository;
        this.gerechtRepository = gerechtRepository;
        this.bestellingRepository = bestellingRepository;
        this.besteldItemRepository = besteldItemRepository;
        this.tafelRepository = tafelRepository;
    }

    @RequestMapping("/")
    public String opvullen(Model model, HttpServletRequest request) {
        if (request.getParameter("opvullen") != null) {
            dbOpvullen();
            model.addAttribute("loaded", true);
            model.addAttribute("gegevens", "De database is opgevuld!");
        }
        return "index";
    }

    private void dbOpvullen() {
        for (int i = 0; i < 10; i++) {
            Zaalpersoneel zaalpersoneel = new Zaalpersoneel();
            zaalpersoneel.setNaam("Zaalpersoneel" + i);
            Keukenpersoneel keukenpersoneel = new Keukenpersoneel();
            keukenpersoneel.setNaam("Keukenpersoneel" + i);
            personeelRepository.save(zaalpersoneel);
            personeelRepository.save(keukenpersoneel);

            Tafel tafel = new Tafel();
            tafel.setCode("" + i);
            tafelRepository.save(tafel);

            Gerecht gerecht = new Gerecht();
            gerecht.setNaam("Gerecht" + i);
            gerecht.setActuelePrijs(Math.pow(i, 2));
            gerechtRepository.save(gerecht);

            Bestelling bestelling = new Bestelling();
            bestelling.setTafel(tafel);
            bestelling.setBetaald(i % 2 == 0);
            bestelling.setDatum((GregorianCalendar) GregorianCalendar.getInstance());
            bestelling.setZaalpersoneel(zaalpersoneel);
            bestelling.addItem(gerecht, 1);
            bestellingRepository.save(bestelling);
        }
    }
}
