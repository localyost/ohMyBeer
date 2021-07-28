package de.omb.ohmybeer.entity.address;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends GenericService<Address, Long, AddressRepository> {

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
    }

}
