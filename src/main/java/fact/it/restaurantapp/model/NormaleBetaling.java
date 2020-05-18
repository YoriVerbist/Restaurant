package fact.it.restaurantapp.model;

public class NormaleBetaling implements BetaalStrategie {
    @Override
    public double getToegepastePrijs(double actuelePrijs) {
        return actuelePrijs;
    }
}
