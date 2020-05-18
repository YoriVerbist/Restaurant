package fact.it.restaurantapp.model;

public class HappyHourBetaling implements BetaalStrategie {

    @Override
    public double getToegepastePrijs(double actuelePrijs) {
        return actuelePrijs * 0.8;
    }
}
