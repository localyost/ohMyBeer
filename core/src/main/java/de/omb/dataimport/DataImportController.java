package de.omb.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/v1/import", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataImportController {

    private final ExcelImport excelImport;

    @Autowired
    DataImportController(ExcelImport excelImport) {
        this.excelImport = excelImport;
    }

    @GetMapping()
    public void start() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("imports/beer.xlsx");
        Path path = Paths.get(url.toURI());
        this.excelImport.run(path.toFile());
    }
}

