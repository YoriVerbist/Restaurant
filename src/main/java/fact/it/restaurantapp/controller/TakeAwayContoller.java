package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.*;
import fact.it.restaurantapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class TakeAwayContoller {

    private BestellingRepository bestellingRepository;
    private PersoneelRepository personeelRepository;
    private GerechtRepository gerechtRepository;
    private TafelRepository tafelRepository;
    private BesteldItemRepository besteldItemRepository;
    private TakeAwayRepository takeAwayRepository;

    public TakeAwayContoller(TakeAwayRepository takeAwayRepository, PersoneelRepository personeelRepository, BesteldItemRepository besteldItemRepository, GerechtRepository gerechtRepository, BestellingRepository bestellingRepository, TafelRepository tafelRepository) {
        this.personeelRepository = personeelRepository;
        this.gerechtRepository = gerechtRepository;
        this.bestellingRepository = bestellingRepository;
        this.tafelRepository = tafelRepository;
        this.besteldItemRepository = besteldItemRepository;
        this.takeAwayRepository = takeAwayRepository;
    }

    @RequestMapping(value = "takeaway", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        List<Personeel> personeelList = personeelRepository.findAll();
        List<Keukenpersoneel> keukenpersoneels = new ArrayList<>();

        List<Gerecht> gerechtList = gerechtRepository.findAll();

        for (Personeel personeel : personeelList) {
            if (personeel instanceof Keukenpersoneel) {
                keukenpersoneels.add((Keukenpersoneel) personeel);
            }
        }


        model.addAttribute("keukenpersoneel", keukenpersoneels);
        model.addAttribute("gerechten", gerechtList);
        return "examen/1_index";
    }

    @RequestMapping(value = "/examen/opslagen", method = RequestMethod.POST)
    public String opslagen(HttpServletRequest request, Model model) {
        String personeel = request.getParameter("personeel");
        String gerechtId = request.getParameter("gerecht");
        String totaal = request.getParameter("aantal");

        Optional<Personeel> keukenpersoneel = personeelRepository.findById(Long.parseLong(personeel));
        Optional<Gerecht> gerecht = gerechtRepository.findById(Long.parseLong(gerechtId));
        int aantal = Integer.parseInt(totaal);

        TakeAwayBestelling takeAwayBestelling = new TakeAwayBestelling();
        takeAwayBestelling.setAantalPersonen(aantal);
        takeAwayBestelling.setKeukenpersoneel((Keukenpersoneel) keukenpersoneel.get());
        takeAwayBestelling.setGerecht((Gerecht) gerecht.get());
        takeAwayBestelling.setDatum((GregorianCalendar) GregorianCalendar.getInstance());
        takeAwayRepository.save(takeAwayBestelling);

        List<TakeAwayBestelling> takeAwayBestellingen = takeAwayRepository.findAll();
        model.addAttribute("bestellingen", takeAwayBestellingen);
        return "examen/2_overzicht";
    }
}
