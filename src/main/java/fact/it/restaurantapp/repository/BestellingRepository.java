package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Long> {

    List<Bestelling> findAllByTafelCodeIsLike(String code);
    List<Bestelling> findAllByDatumBetween(GregorianCalendar start, GregorianCalendar end);
    //List<Bestelling> findAllByDatumContaining(String date);
    //List<Bestelling> findAllByTotaalEquals(double code);

}
