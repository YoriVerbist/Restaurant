package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.Keukenpersoneel;
import fact.it.restaurantapp.model.Tafel;
import fact.it.restaurantapp.model.TakeAwayBestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeAwayRepository extends JpaRepository<TakeAwayBestelling, Long> {
}
