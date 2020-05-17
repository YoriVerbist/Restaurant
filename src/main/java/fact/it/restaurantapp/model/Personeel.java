package fact.it.restaurantapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Personeel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;

    public Personeel() {
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

    public abstract void update(IngangTeller teller);
}
