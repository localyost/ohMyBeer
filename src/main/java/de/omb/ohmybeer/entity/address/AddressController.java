package de.omb.ohmybeer.entity.address;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController extends AbstractController<Address, AddressRepository, AddressService> {
    protected AddressController(AddressService service) {
        super(service);
    }

    @Override
    protected AbstractDTO<Address> createDTO(Address entity, String[] fetchProps) {
        return new AddressDTO(entity);
    }
}
