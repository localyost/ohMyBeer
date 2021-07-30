package de.omb.ohmybeer.entity.socials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialsRepository extends JpaRepository<Socials, Long> {
}
