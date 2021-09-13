package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.AbstractController;
import de.omb.ohmybeer.entity.base.AbstractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "/v1/beers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerController extends AbstractController<Beer, BeerRepository, BeerService> {

    @Autowired
    public BeerController(BeerService beerService) {super(beerService); }

    @Override
    protected AbstractDTO<Beer> createDTO(Beer entity, String[] fetchProps) {
        return new BeerDTO(entity, fetchProps);
    }

    @PostMapping(value = "/image/{id}")
    public boolean submitImages(@PathVariable Long id, @RequestParam MultipartFile[] files) {
        return service.saveImages(id, files);
    }

    @DeleteMapping(value = "/image/{id}/{imageName}")
    public boolean deleteImage(@PathVariable String imageName, @PathVariable Long id) {
        return service.deleteImage(id, imageName);
    }

    @GetMapping(value = "/image/{id}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String id, @PathVariable String imageName) {
        try {
            Path imagePath = Path.of("beerImages").resolve(id).resolve(imageName);
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            return new byte[]{};
        }

    }

}
