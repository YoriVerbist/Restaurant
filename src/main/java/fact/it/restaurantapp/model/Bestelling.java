package fact.it.restaurantapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Bestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Calendar datum;
    private boolean betaald;

    @ManyToOne
    private Tafel tafel;

    @ManyToOne
    private Zaalpersoneel zaalpersoneel;

    @Transient
    private BetaalStrategie betaalStrategie;

    @OneToMany(mappedBy = "bestelling", cascade = {CascadeType.PERSIST})
    private List<BesteldItem> besteldItems = new ArrayList<>();

    public Bestelling() {
        betaalStrategie = new NormaleBetaling();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDatum() {
        return datum;
    }

    public void setDatum(Calendar datum) {
        this.datum = datum;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public List<BesteldItem> getBesteldItems() {
        return besteldItems;
    }

    public void setBesteldItems(List<BesteldItem> besteldItems) {
        this.besteldItems = besteldItems;
    }

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }

    public Zaalpersoneel getZaalpersoneel() {
        return zaalpersoneel;
    }

    public void setZaalpersoneel(Zaalpersoneel zaalpersoneel) {
        this.zaalpersoneel = zaalpersoneel;
    }

    public void addItem(Gerecht gerecht, int aantal) {
        BesteldItem item = new BesteldItem();
        item.setGerecht(gerecht);
        item.setAantal(aantal);
        item.setBestelling(this);
        item.setToegepastePrijs(this.getBetaalStrategie().getToegepastePrijs(gerecht.getActuelePrijs()));
        besteldItems.add(item);
    }

    public double getTotaal() {
        double sum = 0;
        for (BesteldItem besteldItem: besteldItems) {
            sum += besteldItem.getToegepastePrijs() * besteldItem.getAantal();
        }
        return sum;
    }
}
