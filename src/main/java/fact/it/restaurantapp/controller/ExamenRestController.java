package fact.it.restaurantapp.controller;

import fact.it.restaurantapp.model.*;
import fact.it.restaurantapp.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class ExamenRestController {


    private TakeAwayRepository takeAwayRepository;

    public ExamenRestController(TakeAwayRepository takeAwayRepository) {
        this.takeAwayRepository = takeAwayRepository;
    }

//    @GetMapping("/tabestellingen")
//    public List<TakeAwayBestelling> opvragen() {
//        List<TakeAwayBestelling> bestellingen = takeAwayRepository.findAllByKeukenpersoneelOrderByKeukenpersoneel();
//        return bestellingen;
//    }

    @PostMapping("tabestellingen")
    public void opslagen() {
        TakeAwayBestelling bestelling = new TakeAwayBestelling();
        bestelling.setAantalPersonen(2);
        Keukenpersoneel keukenpersoneel = new Keukenpersoneel();
        keukenpersoneel.setNaam("Yori");
        bestelling.setKeukenpersoneel(keukenpersoneel);
        bestelling.setDatum((GregorianCalendar) GregorianCalendar.getInstance());
        Gerecht gerecht = new Gerecht();
        gerecht.setNaam("Pizza");
        gerecht.setActuelePrijs(10.0);
        bestelling.setGerecht(gerecht);
        BetaalStrategie betaalStrategie = new TooGoodToGoBetaling();
        bestelling.setBetaalStrategie(betaalStrategie);
        takeAwayRepository.save(bestelling);
    }

    @DeleteMapping("opleidingen/{id}")
    public void deleten(@PathVariable int position) {
        List<TakeAwayBestelling> takeAwayBestellingen = takeAwayRepository.findAll();
        takeAwayBestellingen.remove(position);
    }
}
