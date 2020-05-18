package fact.it.restaurantapp.model;

public class Administrator extends ExtraTaak {

    public void update() {
        super.update();
        System.out.println("Vervolgens registreer ik de " + IngangTeller.getInstance().getAantal() + " klanten in het klantenbestand.");
    }
}
