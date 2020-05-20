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

    @RequestMapping(value = "/zoeken", method = RequestMethod.GET)
    public String zoeken() {
        return "bestelling/zoeken";
    }

//    @RequestMapping(value = "/zoekenIndex", method = RequestMethod.POST)
//    public String zoekenIndex(HttpServletRequest request, Model model) {
//        String code = request.getParameter("tafel");
//        String date = request.getParameter("datum");
//        String total = request.getParameter("totaal");
//
//        if (code != null) {
//            List<Bestelling> bestellings = bestellingRepository.findAllByTafelCodeIsLike(code);
//        }
//        else if (date != null) {
//            List<Bestelling> bestellings = bestellingRepository.findAllByDatumContaining(date);
//        }
//        else if (total != null) {
//            List<Bestelling> bestellings = bestellingRepository.findAllByTotaalEquals(Double.parseDouble(total));
//        }
//        return "bestellingen/index";
//    }
}
