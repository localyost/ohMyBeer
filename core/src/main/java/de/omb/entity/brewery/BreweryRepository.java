package de.omb.entity.brewery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {

    Brewery findByNameIgnoreCase(String name);

    Set<Brewery> findByNameStartsWithIgnoreCase(String name);
}
