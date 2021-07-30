package de.omb.ohmybeer.entity.webaddress;

import de.omb.ohmybeer.entity.base.AbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/webAddresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebAddressController extends AbstractController<WebAddress, WebAddressRepository, WebAddressService> {
    protected WebAddressController(WebAddressService service) {
        super(service);
    }
}
