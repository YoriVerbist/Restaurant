package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.Keukenpersoneel;
import fact.it.restaurantapp.model.Personeel;
import fact.it.restaurantapp.model.Zaalpersoneel;
import fact.it.restaurantapp.repository.PersoneelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersoneelController {
    private PersoneelRepository personeelRepository;

    public PersoneelController(PersoneelRepository personeelRepository) {
        this.personeelRepository = personeelRepository;
    }

    @RequestMapping("/personeel")
    public String personeelBeheren(HttpServletRequest request, Model model) {
        if (request.getMethod().equals("GET")) {
            List<Personeel> personeelList = personeelRepository.findAll();
            List<Keukenpersoneel> keukenpersoneels = new ArrayList<>();
            List<Zaalpersoneel> zaalpersoneels = new ArrayList<>();

            for (Personeel personeel : personeelList) {
                if (personeel instanceof Keukenpersoneel) {
                    keukenpersoneels.add((Keukenpersoneel) personeel);
                } else {
                    zaalpersoneels.add((Zaalpersoneel) personeel);
                }
            }
            model.addAttribute("zaalpersoneelList", zaalpersoneels);
            model.addAttribute("keukenpersoneelList", keukenpersoneels);
        }
        return "personeel/index";
    }

    @RequestMapping(value = "/personeelToevoegen", method = RequestMethod.POST)
    public RedirectView personeelToevoegen(HttpServletRequest request, Model model) {
        String soort = request.getParameter("soort");
        if (soort.equals("Keukenpersoneel")) {
            Keukenpersoneel persoon = new Keukenpersoneel();
            persoon.setNaam(request.getParameter("naam"));
            personeelRepository.save(persoon);
        } else {
            Zaalpersoneel persoon = new Zaalpersoneel();
            persoon.setNaam(request.getParameter("naam"));
            personeelRepository.save(persoon);
        }
        return new RedirectView("/personeel");
    }
}
