package de.omb.ohmybeer.entity.beer;

import de.omb.ohmybeer.entity.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BeerService extends GenericService<Beer, Long, BeerRepository> {

    @Autowired
    public BeerService(BeerRepository repository) {
        super(repository);
    }

    public boolean saveImages(long id, MultipartFile[] files) {
        final Beer beer = repository.getById(id);
        try {
            File directory = Path.of("beerImages").resolve(beer.getId().toString()).toFile();
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            for (MultipartFile file : files) {
                String fileName = this.getImageName(beer, file);
                Path resolve = directory.toPath().resolve(fileName);
                if (resolve.toFile().exists()) {
                    resolve.toFile().delete();
                }
                Files.copy(file.getInputStream(), resolve);
                String photoPath = getImageName(beer, fileName);
                beer.addPhoto(photoPath);
            }
            repository.save(beer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteImage(Long id, String imageName) {
        final Beer beer = repository.getById(id);
        File image = Path.of("beerImages").resolve(beer.getId().toString()).resolve(imageName).toFile();
        if (image.exists()) {
            if (image.delete()) {
                String photoPath = getImageName(beer, imageName);
                beer.getPhotos().remove(photoPath);
                repository.save(beer);
                return true;
            }
        }
        return false;
    }

    private String getImageName(Beer beer, String imageName) {
        return beer.getId().toString() + "/" + imageName;
    }

    private String getImageName(Beer beer, MultipartFile file) {
        return beer
                .getName().toLowerCase()
                .replace(" ", "_")+"__"+file.getOriginalFilename();
    }
}
