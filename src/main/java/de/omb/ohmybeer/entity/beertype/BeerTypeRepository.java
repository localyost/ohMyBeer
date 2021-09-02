package de.omb.ohmybeer.entity.beertype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BeerTypeRepository extends JpaRepository<BeerType, Long> {

    BeerType findByNameIgnoreCase(String name);

    Set<BeerType> findByNameStartsWithIgnoreCase(String name);
}
