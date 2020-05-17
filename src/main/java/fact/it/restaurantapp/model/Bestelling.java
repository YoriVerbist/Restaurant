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

    @OneToMany(mappedBy = "bestelling")
    private List<BesteldItem> besteldItems = new ArrayList<>();

    public Bestelling() {
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
}
