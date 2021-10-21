package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.BreweryImportExcelColumn;
import de.omb.ohmybeer.dataimport.excel.ExcelParsingMap;
import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.brewery.Brewery;
import de.omb.ohmybeer.entity.brewery.BreweryService;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BreweryImport {

    private final BreweryService breweryService;

    public void run(List<ExcelParsingMap> elementsToParse) {
        elementsToParse.forEach(this::createBrewery);
    }

    private void createBrewery(ExcelParsingMap map) {
        Optional<Object> breweryNameOpt = map.get(BreweryImportExcelColumn.breweryName);
        Optional<Object> infoOpt = map.get(BreweryImportExcelColumn.info);
        Optional<Object> legalOpt = map.get(BreweryImportExcelColumn.legal);
        Optional<Object> tipp = map.get(BreweryImportExcelColumn.ombTip);

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

            tipp.ifPresent(o -> {
                brewery.setOmbRecommend(true);
            });
        }
    }

    private void createAddress(Brewery brewery, ExcelParsingMap map) {

        final Address address = ExcelImport.parseAddress(
                map.get(BreweryImportExcelColumn.latitude),
                map.get(BreweryImportExcelColumn.longitude),
                map.get(BreweryImportExcelColumn.street),
                map.get(BreweryImportExcelColumn.houseNumber),
                map.get(BreweryImportExcelColumn.city),
                map.get(BreweryImportExcelColumn.country),
                map.get(BreweryImportExcelColumn.postalCode)
        );
        brewery.setAddress(address);
        createSocialMedia(brewery, map);
    }

    private void createSocialMedia(Brewery brewery, ExcelParsingMap map) {
        final Socials socials = ExcelImport.createSocials(
                map.get(BreweryImportExcelColumn.website),
                map.get(BreweryImportExcelColumn.facebook),
                map.get(BreweryImportExcelColumn.instagram),
                map.get(BreweryImportExcelColumn.twitter));

        brewery.setSocials(socials);
        this.breweryService.save(brewery);
    }



}
