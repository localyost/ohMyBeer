package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/v1/beers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerController extends AbstractController<Beer, BeerRepository, BeerService> {

    @Autowired
    public BeerController(BeerService beerService) { super(beerService); }

    @Override
    protected AbstractDTO<Beer> createDTO(Beer entity, String[] fetchProps) {
        return new BeerDTO(entity, fetchProps);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        var imgFile = new ClassPathResource("static/6beers.png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
