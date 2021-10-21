package de.omb.webapi.controller.socials;

import de.omb.entity.socials.Socials;
import de.omb.entity.socials.SocialsRepository;
import de.omb.entity.socials.SocialsService;
import de.omb.webapi.controller.base.AbstractController;
import de.omb.webapi.controller.base.AbstractDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/socials", produces = MediaType.APPLICATION_JSON_VALUE)
public class SocialsController extends AbstractController<Socials, SocialsRepository, SocialsService> {
    protected SocialsController(SocialsService service) {
        super(service);
    }

    @Override
    protected AbstractDTO<Socials> createDTO(Socials entity, String[] fetchProps) {
        return new SocialsDTO(entity);
    }
}
