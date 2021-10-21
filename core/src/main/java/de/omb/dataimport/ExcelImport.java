package de.omb.dataimport;

import de.omb.dataimport.excel.BeerImportExcelColumn;
import de.omb.dataimport.excel.BreweryImportExcelColumn;
import de.omb.dataimport.excel.ExcelParsingMap;
import de.omb.dataimport.excel.RestaurantImportExcelColumn;
import de.omb.entity.address.Address;
import de.omb.entity.socials.Socials;
import de.omb.enums.Country;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExcelImport {

    private final BreweryImport breweryImport;
    private final BeerImport beerImport;
    private final RestaurantImport restaurantImport;

    public void run(File importFile) {
        try {
            FileInputStream fis = new FileInputStream(importFile);
            Workbook workbook = new XSSFWorkbook(fis);
            this.beerImport.run(this.extractElements(workbook.getSheetAt(0), BeerImportExcelColumn.class));
            this.breweryImport.run(this.extractElements(workbook.getSheetAt(1), BreweryImportExcelColumn.class));
            this.restaurantImport.run(this.extractElements(workbook.getSheetAt(2), RestaurantImportExcelColumn.class));
        } catch (IOException e) {

        }
    }

    private <T extends Enum<T>> List<ExcelParsingMap> extractElements(Sheet sheet, Class<T> columnClass) {
        final List<ExcelParsingMap> elementsToParse = new ArrayList<>();
        int rowIndex = 0;
        int HEADERS_ROW = 0;
        for (Row row : sheet) {
            if (rowIndex > HEADERS_ROW) {
                ExcelParsingMap parsingMap = new ExcelParsingMap();
                EnumSet.allOf(columnClass).forEach(column -> parsingMap.addElement(column, row));
                elementsToParse.add(parsingMap);
            }
            rowIndex++;
        }
        return elementsToParse;
    }

    static Country getCountry(String countryName) {
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

    static Address parseAddress(
            Optional<Object> latOpt,
            Optional<Object> lngOpt,
            Optional<Object> streetOpt,
            Optional<Object> houseNumberOpt,
            Optional<Object> cityOpt,
            Optional<Object> countryOpt,
            Optional<Object> postCodeOpt
    ) {
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
            address.setCountry(ExcelImport.getCountry(countryName));
        });
        postCodeOpt.ifPresent(o -> address.setPostcode(o.toString().trim()));

        return address;
    }

    static Socials createSocials(
            Optional<Object> websiteObt,
            Optional<Object> facebookObt,
            Optional<Object> instagramObt,
            Optional<Object> twitterObt) {
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
        return socials;
    }

}
