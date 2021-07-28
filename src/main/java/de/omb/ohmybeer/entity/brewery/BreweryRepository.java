package de.omb.ohmybeer.entity.brewery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {

    @Query("select brewery from Brewery as brewery where brewery.name = :name")
    Brewery getByName(@Param("name") String name);

}
