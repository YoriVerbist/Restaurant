package fact.it.restaurantapp.model;

import javax.persistence.ManyToOne;

public abstract class ExtraTaak extends Personeel{

    @ManyToOne
    private Personeel personeel;

    public void update() {
        personeel.update();
    }

    public Personeel getPersoneel() {
        return personeel;
    }

    public void setPersoneel(Personeel personeel) {
        this.personeel = personeel;
    }
}
