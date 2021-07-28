package de.omb.ohmybeer.entity.beertype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerTypeRepository extends JpaRepository<BeerType, Long> {

    @Query("select bt from BeerType as bt where bt.name = :name")
    BeerType getByName(@Param("name") String name);
}
