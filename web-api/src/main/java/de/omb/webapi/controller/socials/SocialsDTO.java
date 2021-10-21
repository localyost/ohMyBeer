package de.omb.webapi.controller.socials;

import de.omb.entity.socials.Socials;
import de.omb.webapi.controller.base.AbstractDTO;
import lombok.Getter;

@Getter
public class SocialsDTO extends AbstractDTO<Socials> {

    private String website;
    private String facebook;
    private String instagram;
    private String twitter;

    protected SocialsDTO(Socials entity) {
        super(entity);
    }

    @Override
    protected void setProperties() {
        website = entity.getWebsite();
        facebook = entity.getFacebook();
        instagram = entity.getInstagram();
        twitter = entity.getTwitter();
    }
}
