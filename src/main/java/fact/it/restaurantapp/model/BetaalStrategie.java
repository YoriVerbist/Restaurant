package fact.it.restaurantapp.model;

public interface BetaalStrategie {
    abstract double getToegepastePrijs(double actuelePrijs);
}
