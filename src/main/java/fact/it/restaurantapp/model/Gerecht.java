package fact.it.restaurantapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gerecht {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private double actuelePrijs;

    public Gerecht() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getActuelePrijs() {
        return actuelePrijs;
    }

    public void setActuelePrijs(double actuelePrijs) {
        this.actuelePrijs = actuelePrijs;
    }
}
