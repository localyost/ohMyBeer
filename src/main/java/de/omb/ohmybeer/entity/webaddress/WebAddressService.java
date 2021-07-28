package de.omb.ohmybeer.entity.webaddress;

import de.omb.ohmybeer.entity._base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAddressService extends GenericService<WebAddress, Long, WebAddressRepository> {


    @Autowired
    public WebAddressService(WebAddressRepository webAddressRepository) {
        super(webAddressRepository);
    }
}
