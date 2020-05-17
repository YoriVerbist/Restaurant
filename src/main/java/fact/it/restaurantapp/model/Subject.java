package fact.it.restaurantapp.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    public abstract void attachObserver(Personeel observer);
    public abstract void detachObserver(Personeel observer);
    public abstract void notifyObservers();

    @OneToMany(mappedBy = "subject")
    public List<Personeel> observers = new ArrayList<>();

    public List<Personeel> getObservers() {
        return observers;
    }

    public void setObservers(List<Personeel> observers) {
        this.observers = observers;
    }
}
