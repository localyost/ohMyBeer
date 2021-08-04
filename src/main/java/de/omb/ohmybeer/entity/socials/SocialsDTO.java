package de.omb.ohmybeer.entity.socials;

import de.omb.ohmybeer.entity.base.AbstractDTO;
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
    protected AbstractDTO<Socials> setProperties() {
        website = entity.getWebsite();
        facebook = entity.getFacebook();
        instagram = entity.getInstagram();
        twitter = entity.getTwitter();
        return this;
    }
}
