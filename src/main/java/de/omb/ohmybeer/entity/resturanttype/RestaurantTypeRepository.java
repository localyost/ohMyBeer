package de.omb.ohmybeer.entity.resturanttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {
    RestaurantType findByNameIgnoreCase(String name);
}
