package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.*;
import fact.it.restaurantapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BestellingController {
    private BestellingRepository bestellingRepository;
    private PersoneelRepository personeelRepository;
    private GerechtRepository gerechtRepository;
    private TafelRepository tafelRepository;
    private BesteldItemRepository besteldItemRepository;

    private Map<BesteldItem, Integer> items = new LinkedHashMap<>();


    public BestellingController(PersoneelRepository personeelRepository, BesteldItemRepository besteldItemRepository, GerechtRepository gerechtRepository, BestellingRepository bestellingRepository, TafelRepository tafelRepository) {
        this.personeelRepository = personeelRepository;
        this.gerechtRepository = gerechtRepository;
        this.bestellingRepository = bestellingRepository;
        this.tafelRepository = tafelRepository;
        this.besteldItemRepository = besteldItemRepository;
    }

    @RequestMapping(value = "/bestellingen", method = RequestMethod.GET)
    public String bestellingenWeergeven(HttpServletRequest request, Model model) {
        List<Bestelling> bestellings = bestellingRepository.findAll();
        items.clear();
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
                String dateString = date + " 00:00:00:00";
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
                if (bestelling.getTotaal() >= totaalParsed) {
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

    @RequestMapping(value = "/bestelling/create", method = RequestMethod.GET)
    public String paginaTonen(HttpServletRequest request, Model model) {
        List<Bestelling> bestellingList = bestellingRepository.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        String date = dateFormat.format(now.getTime());

        model.addAttribute("bestellings", bestellingList);
        model.addAttribute("items", items);
        model.addAttribute("date", date);

        return "bestelling/create";
    }

    @RequestMapping(value = "/bestelling/createBestelling", method = RequestMethod.POST)
    public RedirectView bestellingToevoegen(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("zaalpersoneel"));
        Optional<Personeel> zaalpersoneel = personeelRepository.findById(id);
        Long tafelNr = Long.parseLong(request.getParameter("tafel"));
        Optional<Tafel> tafel = tafelRepository.findById(tafelNr);
        String datum = request.getParameter("datum");
        try {
            Bestelling bestelling = new Bestelling();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = dateFormat.parse(datum);
            GregorianCalendar bestellingDatum = (GregorianCalendar) GregorianCalendar.getInstance();
            bestellingDatum.setTime(parsed);
            bestelling.setDatum(bestellingDatum);

            if (request.getParameter("betaald") != null) {
                bestelling.setBetaald(true);
            }

            if (zaalpersoneel.isPresent() && zaalpersoneel.get() instanceof Zaalpersoneel) {
                bestelling.setZaalpersoneel((Zaalpersoneel) zaalpersoneel.get());
            }
            tafel.ifPresent(bestelling::setTafel);

            for (Map.Entry<BesteldItem, Integer> entry : items.entrySet()) {
                BesteldItem besteldItem = entry.getKey();
                int methode = entry.getValue();
                if (methode == 1) {
                    bestelling.setBetaalStrategie(new NormaleBetaling());
                } else {
                    bestelling.setBetaalStrategie(new HappyHourBetaling());
                }
                bestelling.addItem(besteldItem.getGerecht(), besteldItem.getAantal());
                bestellingRepository.save(bestelling);
            }
        } catch (Exception e) {
            return new RedirectView("/bestellingen/");
        }
        return new RedirectView("/bestellingen/");
    }

    @RequestMapping(value = "/bestelling/item", method = RequestMethod.GET)
    public String gerechtToevoegen(HttpServletRequest request, Model model) {
        List<Gerecht> gerechtList = gerechtRepository.findAll();
        model.addAttribute("gerechten", gerechtList);

        return "bestelling/item";
    }

    @RequestMapping(value = "bestelling/item", method = RequestMethod.POST)
    public RedirectView terugNaarCreate(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("gerecht"));
        int aantal = Integer.parseInt(request.getParameter("aantal"));
        int methode = Integer.parseInt(request.getParameter("methode"));

        Optional<Gerecht> gerecht = gerechtRepository.findById(id);

        if (gerecht.isPresent()) {
            BesteldItem besteldItem = new BesteldItem();
            besteldItem.setGerecht(gerecht.get());
            besteldItem.setAantal(aantal);
            items.put(besteldItem, methode);
        }
        return new RedirectView("/bestelling/create");
    }
}
