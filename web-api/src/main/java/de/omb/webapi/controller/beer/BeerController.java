package de.omb.webapi.controller.beer;

import de.omb.entity.beer.Beer;
import de.omb.entity.beer.BeerRepository;
import de.omb.entity.beer.BeerService;
import de.omb.webapi.controller.base.AbstractController;
import de.omb.webapi.controller.base.AbstractDTO;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/v1/beers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerController extends AbstractController<Beer, BeerRepository, BeerService> {

    private MinioClient minioClient;

    @Autowired
    public void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public BeerController(BeerService beerService) {super(beerService); }

    @Override
    protected AbstractDTO<Beer> createDTO(Beer entity, String[] fetchProps) {
        return new BeerDTO(entity, fetchProps);
    }

    @PostMapping(value = "/image/{id}")
    public boolean uploadImages(@PathVariable Long id, @RequestParam MultipartFile[] files) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Beer beer = service.getOne(id);
        //todo set Tags for minio?
        int index = 0;
        for (MultipartFile file : files) {
            final String imageName = String.format("%s/%s%s",
                    beer.getBrewery().getName(),
                    beer.getName(),
                    index > 0 ? "_"+index : "");

            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket("beerimages")
                    .object(imageName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType("image/png")
                    .build();
            ObjectWriteResponse response = minioClient.putObject(args);
            //todo evaluate if we even need to save images to Beer if we maintain the same schema "brewery/beer"
            beer.getPhotos().add(response.object());
            service.save(beer);
            index++;
        }
        return false;
    }

}
