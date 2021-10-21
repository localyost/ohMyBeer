package de.omb.entity.socials;

import de.omb.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialsService extends GenericService<Socials, Long, SocialsRepository> {


    @Autowired
    public SocialsService(SocialsRepository socialsRepository) {
        super(socialsRepository);
    }
}
