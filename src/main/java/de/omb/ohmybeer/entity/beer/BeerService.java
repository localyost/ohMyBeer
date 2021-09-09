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


    public boolean uploadImages(long id, MultipartFile[] files) {
        final Beer beer = repository.getById(id);
        try {
            File directory = Path.of("beerImages").resolve(beer.getId().toString()).toFile();
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            for (MultipartFile file : files) {
                String fileName = beer.getName().toLowerCase().replace(" ", "_");
                fileName = fileName +"__"+ file.getOriginalFilename();
                Path resolve = directory.toPath().resolve(fileName);
                if (resolve.toFile().exists()) {
                    resolve.toFile().delete();
                }
                Files.copy(file.getInputStream(), resolve);
                String photoPath = beer.getId().toString() + "/" + fileName;
                beer.addPhoto(photoPath);
            }
            repository.save(beer);
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }
}
