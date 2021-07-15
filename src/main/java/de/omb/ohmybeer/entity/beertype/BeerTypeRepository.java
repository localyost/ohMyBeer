package de.omb.ohmybeer.entity.beertype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerTypeRepository extends JpaRepository<BeerType, Long> {}
