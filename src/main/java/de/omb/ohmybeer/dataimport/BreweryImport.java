package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.BreweryImportExcelColumn;
import de.omb.ohmybeer.dataimport.excel.ExcelParsingMap;
import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.address.AddressService;
import de.omb.ohmybeer.entity.brewery.Brewery;
import de.omb.ohmybeer.entity.brewery.BreweryService;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.socials.SocialsService;
import de.omb.ohmybeer.entity.translation.Translation;
import de.omb.ohmybeer.enums.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BreweryImport {

    private final BreweryService breweryService;
    private final AddressService addressService;
    private final SocialsService socialsService;

    @Autowired
    public BreweryImport(
            BreweryService breweryService,
            AddressService addressService,
            SocialsService socialsService) {
        this.breweryService = breweryService;
        this.addressService = addressService;
        this.socialsService = socialsService;
    }

    public void run(List<ExcelParsingMap> elementsToParse) {
        elementsToParse.forEach(this::createBrewery);
    }

    private Brewery createBrewery(ExcelParsingMap map) {
        Optional<Object> breweryNameOpt = map.get(BreweryImportExcelColumn.breweryName);
        Optional<Object> infoOpt = map.get(BreweryImportExcelColumn.info);
        Optional<Object> legalOpt = map.get(BreweryImportExcelColumn.legal);

        if (breweryNameOpt.isPresent()) {
            String breweryName = breweryNameOpt.get().toString().trim();
            Optional<Brewery> breweryOptional = Optional.ofNullable(breweryService.getByName(breweryName));
            final Brewery brewery = breweryOptional.orElse(new Brewery());
            brewery.setName(breweryName);

            infoOpt.ifPresent(o -> {
                String info = o.toString().trim();
                Translation translation = new Translation();
                translation.setDe(info);
                brewery.setInformation(translation);
            });

            legalOpt.ifPresent(o -> {
                String legal = o.toString().trim();
                brewery.setLegalEntity(legal);
            });
            this.createAddress(brewery, map);
        }
        return null;
    }

    private Brewery createAddress(Brewery brewery, ExcelParsingMap map) {

        Optional<Object> latOpt = map.get(BreweryImportExcelColumn.latitude);
        Optional<Object> lngOpt = map.get(BreweryImportExcelColumn.longitude);
        Optional<Object> streetOpt = map.get(BreweryImportExcelColumn.street);
        Optional<Object> houseNumberOpt = map.get(BreweryImportExcelColumn.houseNumber);
        Optional<Object> cityOpt = map.get(BreweryImportExcelColumn.city);
        Optional<Object> countryOpt = map.get(BreweryImportExcelColumn.country);
        Optional<Object> postCodeOpt = map.get(BreweryImportExcelColumn.postalCode);

        final Address address = new Address();
        latOpt.ifPresent(o -> {
            double lat = (double) o;
            address.setLatitude(lat);
        });
        lngOpt.ifPresent(o -> {
            double lng = (double) o;
            address.setLongitude(lng);
        });
        streetOpt.ifPresent(o -> address.setStreet(o.toString().trim()));
        houseNumberOpt.ifPresent(o -> address.setHouseNumber(o.toString().trim()));
        cityOpt.ifPresent(o -> address.setCity(o.toString().trim()));
        countryOpt.ifPresent(o -> {
            String countryName = o.toString().trim();
            address.setCountry(this.getCountry(countryName));
        });
        postCodeOpt.ifPresent(o -> address.setPostcode(o.toString().trim()));
        brewery.setAddress(address);
        return createSocialMedia(brewery, map);
    }

    private Brewery createSocialMedia(Brewery brewery, ExcelParsingMap map) {
        Optional<Object> websiteObt = map.get(BreweryImportExcelColumn.website);
        Optional<Object> facebookObt = map.get(BreweryImportExcelColumn.facebook);
        Optional<Object> instagramObt = map.get(BreweryImportExcelColumn.instagram);
        Optional<Object> twitterObt = map.get(BreweryImportExcelColumn.twitter);

        final Socials socials = new Socials();

        websiteObt.ifPresent(o -> {
            String text = o.toString().trim();
            socials.setWebsite(text);
        });

        facebookObt.ifPresent(o -> {
            String text = o.toString().trim();
            socials.setFacebook(text);
        });

        instagramObt.ifPresent(o -> {
            String text = o.toString().trim();
            socials.setInstagram(text);
        });

        twitterObt.ifPresent(o -> {
            String text = o.toString().trim();
            socials.setTwitter(text);
        });
        brewery.setSocials(socials);
        this.breweryService.save(brewery);
        return brewery;
    }


    private Country getCountry(String countryName) {
        switch (countryName) {
            case "Deutschland":
                return Country.DE;
            case "Österreich":
                return Country.AT;
            case "Niederlande":
                return Country.NL;
            case "Tschechien":
                return Country.CZ;
            default:
                return Country.UNKNOWN;
        }
    }

}
