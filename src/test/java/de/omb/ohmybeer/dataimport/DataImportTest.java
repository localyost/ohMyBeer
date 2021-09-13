package de.omb.ohmybeer.dataimport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class DataImportTest {

    @Autowired
    private ExcelImport excelImport;

    @Test
    void testExcelImport() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("imports/beer.xlsx");
        Path path = Paths.get(url.toURI());
        excelImport.run(path.toFile());
    }
}
