package fact.it.restaurantapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
public class TakeAwayBestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // In de opgave staat dat dit gewoon aantal moet zijn, maar dan werken de tests niet want daar noemt dit fiels aantalPersonen.
    private int aantalPersonen;
    private GregorianCalendar datum;

    @ManyToOne
    private Keukenpersoneel keukenpersoneel;

    @ManyToOne
    private Gerecht gerecht;

    @Transient
    @JsonIgnore
    private BetaalStrategie betaalStrategie;

    public TakeAwayBestelling() {
        betaalStrategie = new NormaleBetaling();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public GregorianCalendar getDatum() {
        return datum;
    }

    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    public Keukenpersoneel getKeukenpersoneel() {
        return keukenpersoneel;
    }

    public void setKeukenpersoneel(Keukenpersoneel keukenpersoneel) {
        this.keukenpersoneel = keukenpersoneel;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public void setGerecht(Gerecht gerecht) {
        this.gerecht = gerecht;
    }

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }

    public double getTotaal() {
        double sum = aantalPersonen * this.getBetaalStrategie().getToegepastePrijs(gerecht.getActuelePrijs());
        return sum;
    }
}
