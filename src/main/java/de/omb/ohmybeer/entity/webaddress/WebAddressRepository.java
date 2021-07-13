package de.omb.ohmybeer.entity.webaddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAddressRepository extends JpaRepository<WebAddress, Long> {
}
