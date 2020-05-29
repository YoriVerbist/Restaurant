package fact.it.restaurantapp.model;

public class TooGoodToGoBetaling implements BetaalStrategie{

    @Override
    public double getToegepastePrijs(double actuelePrijs) {
        return actuelePrijs * 0.5;
    }
}
