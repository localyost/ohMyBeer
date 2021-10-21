package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.ExcelParsingMap;
import de.omb.ohmybeer.dataimport.excel.RestaurantImportExcelColumn;
import de.omb.ohmybeer.entity.address.Address;
import de.omb.ohmybeer.entity.beer.Beer;
import de.omb.ohmybeer.entity.beer.BeerService;
import de.omb.ohmybeer.entity.restaurant.Restaurant;
import de.omb.ohmybeer.entity.restaurant.RestaurantService;
import de.omb.ohmybeer.entity.resturanttype.RestaurantType;
import de.omb.ohmybeer.entity.resturanttype.RestaurantTypeService;
import de.omb.ohmybeer.entity.socials.Socials;
import de.omb.ohmybeer.entity.translation.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantImport {

    private final RestaurantService restaurantService;
    private final RestaurantTypeService restaurantTypeService;
    private final BeerService beerService;

    public void run(List<ExcelParsingMap> elementsToParse) {
        elementsToParse.forEach(this::createRestaurant);
    }

    private void createRestaurant(ExcelParsingMap map) {
        Optional<Object> nameOpt = map.get(RestaurantImportExcelColumn.name);
        Optional<Object> infoOpt = map.get(RestaurantImportExcelColumn.text);
        Optional<Object> tipp = map.get(RestaurantImportExcelColumn.ombTip);

        if (nameOpt.isPresent()) {
            String name = nameOpt.get().toString().trim();
            Optional<Restaurant> restaurantOptional = Optional.ofNullable(restaurantService.getByName(name));
            final Restaurant restaurant = restaurantOptional.orElse(new Restaurant());
            restaurant.setName(name);

            infoOpt.ifPresent(o -> {
                String info = o.toString().trim();
                Translation translation = new Translation();
                translation.setDe(info);
                restaurant.setInformation(translation);
            });

            tipp.ifPresent(o -> restaurant.setOmbRecommend(true));

            createAddress(restaurant, map);
        }
    }

    private void createAddress(Restaurant restaurant, ExcelParsingMap map) {
        final Address address = ExcelImport.parseAddress(
                map.get(RestaurantImportExcelColumn.latitude),
                map.get(RestaurantImportExcelColumn.longitude),
                map.get(RestaurantImportExcelColumn.street),
                map.get(RestaurantImportExcelColumn.houseNumber),
                map.get(RestaurantImportExcelColumn.city),
                map.get(RestaurantImportExcelColumn.country),
                map.get(RestaurantImportExcelColumn.postalCode)
        );
        restaurant.setAddress(address);
        createSocialMedia(restaurant, map);
    }

    private void createSocialMedia(Restaurant restaurant, ExcelParsingMap map) {
        final Socials socials = ExcelImport.createSocials(
                map.get(RestaurantImportExcelColumn.website),
                map.get(RestaurantImportExcelColumn.facebook),
                map.get(RestaurantImportExcelColumn.instagram),
                map.get(RestaurantImportExcelColumn.twitter));
        restaurant.setSocials(socials);
        createRestaurantTypes(map, restaurant);
    }

    private void createRestaurantTypes(ExcelParsingMap map, Restaurant restaurant) {
        Optional<Object> typesOpt = map.get(RestaurantImportExcelColumn.type);
        if (typesOpt.isPresent()) {
            final String typesStr = typesOpt.get().toString().trim();
            String[] arry = typesStr.split(",");
            List<String> explodedNames = Arrays.asList(arry);
            Set<RestaurantType> restaurantTypeSet = explodedNames.stream().map(explodedName -> {
                String trimmed = explodedName.trim();
                Optional<RestaurantType> rto = Optional.ofNullable(this.restaurantTypeService.getByName(trimmed));
                final RestaurantType restaurantType = rto.orElse(new RestaurantType());
                restaurantType.setName(trimmed);
                return restaurantTypeService.save(restaurantType);
            }).collect(Collectors.toSet());
           restaurant.setRestaurantTypes(restaurantTypeSet);
        }
        setSignatureBeer(map, restaurant);
    }

    private void setSignatureBeer(ExcelParsingMap map, Restaurant restaurant) {
        Optional<Object> signatureBeer = map.get(RestaurantImportExcelColumn.signatureBeer);
        if (signatureBeer.isPresent()) {
            String beerName = signatureBeer.get().toString().trim();
            System.out.println(beerName);
            Beer beer = beerService.getByName(beerName);
            if (beer != null) {
                restaurant.setSignatureBeer(beer);
            }
        }
        setBeers(map, restaurant);
    }

    private void setBeers(ExcelParsingMap map, Restaurant restaurant) {
        Optional<Object> beersOpt = map.get(RestaurantImportExcelColumn.beers);
        if (beersOpt.isPresent()) {
            final String beerNames = beersOpt.get().toString().trim();
            String[] arry = beerNames.split(",");
            List<String> explodedNames = Arrays.asList(arry);
            Set<Beer> beers = explodedNames.stream()
                    .map(String::trim)
                    .peek(System.out::println)
                    .map(explodedName -> beerService.getByName(explodedName.trim()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            restaurant.setBeers(beers);
        }
        System.out.println(restaurant.getName());

        restaurantService.save(restaurant);
    }

}
