package fact.it.restaurantapp.model;

public class Poetspersoon extends ExtraTaak{
    public void update() {
        super.update();
        System.out.println("Vervolgens registreer ik de " + IngangTeller.getInstance().getAantal() + " klanten in het klantenbestand.");
    }

    public void schoonMaken() {
        System.out.println("Ik ben " + this.getPersoneel().getNaam() + " en ik ga nu ook schoonmaken");
    }
}
