package fact.it.restaurantapp.model;

import javax.persistence.Entity;

@Entity
public class Keukenpersoneel extends Personeel {
    public Keukenpersoneel() {
    }

    @Override
    public void update(IngangTeller teller) {
        System.out.println("Ik ben " + this.getNaam() + " en ik begin onmiddellijk met het maken van " + teller.getAantal() + " amuse-gueules!");
    }
}
