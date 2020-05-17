package fact.it.restaurantapp.model;

import javax.persistence.Entity;

@Entity
public class Zaalpersoneel extends Personeel {
    public Zaalpersoneel() {
    }

    @Override
    public void update(IngangTeller teller) {
        System.out.println("Ik ben " + this.getNaam() + " en ga het nodige doen om voor " + teller.getAantal() + " klanten een tafel klaar te maken.");
    }
}
