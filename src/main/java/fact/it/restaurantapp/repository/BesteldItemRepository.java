package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.BesteldItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BesteldItemRepository extends JpaRepository<BesteldItem, Long> {
}
