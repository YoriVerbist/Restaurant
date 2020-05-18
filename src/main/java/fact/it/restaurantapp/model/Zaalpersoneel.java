package fact.it.restaurantapp.model;

import javax.persistence.Entity;

@Entity
public class Zaalpersoneel extends Personeel {
    public Zaalpersoneel() {
    }

    @Override
    public void update() {
        System.out.println("Ik ben " + this.getNaam() + " en ga het nodige doen om voor " + IngangTeller.getInstance().getAantal() + " klanten een tafel klaar te maken.");
    }
}
