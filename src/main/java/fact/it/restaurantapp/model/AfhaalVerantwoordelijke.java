package fact.it.restaurantapp.model;

public class AfhaalVerantwoordelijke extends ExtraTaak{
    public void leverBestellingAf() {
        System.out.println("AfhaalVerantwoordelijke " + this.getPersoneel().getNaam() + " geeft de bestelling door aan de klant");
    }

    @Override
    public void update() {
        System.out.println("AfhaalVerantwoordelijke " + this.getPersoneel().getNaam() + " heet de klant welkom en vraagt het bestelnummer op");
    }
}
