package de.omb.ohmybeer.dataimport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ExcelImport {

    public void start() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("imports/beer.xlsx");
        try {
            if (url != null) {
                Path path = Paths.get(url.toURI());
                FileInputStream fis = new FileInputStream(path.toFile());
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                int i = 0;
                for(Row row : sheet) {
                    if (i != 0) {
                        for (Cell cell : row) {
                            System.out.println(cell);
                        }
                    }
                   i++;
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
