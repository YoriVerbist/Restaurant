package fact.it.restaurantapp.model;

public class Poetspersoon extends ExtraTaak{
    public void schoonMaken() {
        System.out.println("Ik ben " + this.getPersoneel().getNaam() + " en ik ga nu ook schoonmaken");
    }
}
