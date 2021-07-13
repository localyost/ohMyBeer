package de.omb.ohmybeer.entity.brewery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {}
