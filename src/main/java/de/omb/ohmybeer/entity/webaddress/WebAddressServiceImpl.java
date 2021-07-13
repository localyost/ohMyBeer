package de.omb.ohmybeer.entity.webaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAddressServiceImpl implements WebAddressService {

    WebAddressRepository webAddressRepository;

    @Autowired
    public WebAddressServiceImpl(WebAddressRepository webAddressRepository) {
        this.webAddressRepository = webAddressRepository;
    }

    @Override
    public WebAddress addWebAddress(WebAddress webAddress) {
        return webAddressRepository.save(webAddress);
    }
}
