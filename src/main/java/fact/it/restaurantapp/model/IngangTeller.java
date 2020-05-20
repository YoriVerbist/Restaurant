package fact.it.restaurantapp.model;

public class IngangTeller extends Subject {

    private static IngangTeller instance;
    private int aantal;

    private IngangTeller() {
    }

    public static IngangTeller getInstance() {
        if (instance == null) {
            instance = new IngangTeller();
        }
        return instance;
    }

    public static void setInstance(IngangTeller instance) {
        IngangTeller.instance = instance;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
        notifyObservers();
    }

    @Override
    public void attachObserver(Personeel observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(Personeel observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Personeel observer : observers) {
            observer.update();
        }
    }
}
