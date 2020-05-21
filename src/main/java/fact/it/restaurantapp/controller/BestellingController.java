package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.BesteldItem;
import fact.it.restaurantapp.model.Bestelling;
import fact.it.restaurantapp.repository.BestellingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String zoeken(HttpServletRequest request, Model model) {
        List<Bestelling> bestellings = bestellingRepository.findAll();
        model.addAttribute("bestellingList", bestellings);
        return "bestelling/zoeken";
    }

    @RequestMapping(value = "/zoekenIndex", method = RequestMethod.POST)
    public String zoekenIndex(HttpServletRequest request, Model model) {
        String tafel = request.getParameter("tafel");
        String totaal = request.getParameter("totaal");
        List<Bestelling> bestellingList = new ArrayList<>();

        if (tafel != null) {
            bestellingList = this.bestellingRepository.findAllByTafelCodeIsLike(tafel);
            model.addAttribute("bestellingList", bestellingList);
        }
        if (request.getParameter("datum").length() > 0) {
            String date = request.getParameter("datum");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:ms");
            try {
                String dateString = date+" 00:00:00:00";
                Date parsedDate = dateFormat.parse(dateString);
                GregorianCalendar startDate = (GregorianCalendar) GregorianCalendar.getInstance();
                startDate.setTime(parsedDate);
                GregorianCalendar endDate = (GregorianCalendar) GregorianCalendar.getInstance();
                endDate.setTime(parsedDate);
                endDate.add(Calendar.DAY_OF_MONTH, 1);
                System.out.println(startDate.getTime());
                System.out.println(endDate.getTime());

                bestellingList = this.bestellingRepository.findAllByDatumBetween(startDate, endDate);
                model.addAttribute("bestellingList", bestellingList);
            } catch (ParseException e) {
                return "/bestelling/index";
            }
        }
        if (totaal.length() > 0) {
            List<Bestelling> bestellings = bestellingRepository.findAll();
            for (Bestelling bestelling : bestellings) {
                double totaalParsed = Double.parseDouble(totaal);
                if (bestelling.getTotaal() == totaalParsed) {
                    bestellingList.add(bestelling);
                }
            }
            model.addAttribute("bestellingList", bestellingList);
        }
        return "/bestelling/index";
    }

    @RequestMapping(value = "/bestelling/detail", method = RequestMethod.GET)
    public String detailPagina(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        if (bestellingRepository.existsById(id)) {
            Optional<Bestelling> bestelling = this.bestellingRepository.findById(id);
            model.addAttribute("bestelling", bestelling.get());
            return "/bestelling/detail";
        }
        return "/bestelling/index";
    }
}
