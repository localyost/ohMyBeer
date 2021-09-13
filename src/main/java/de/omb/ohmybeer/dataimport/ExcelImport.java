package de.omb.ohmybeer.dataimport;

import de.omb.ohmybeer.dataimport.excel.BeerImportExcelColumn;
import de.omb.ohmybeer.dataimport.excel.BreweryImportExcelColumn;
import de.omb.ohmybeer.dataimport.excel.ExcelParsingMap;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
public class ExcelImport {
    private final BreweryImport breweryImport;
    private final BeerImport beerImport;

    @Autowired
    public ExcelImport(BreweryImport breweryImport, BeerImport beerImport) {
        this.breweryImport = breweryImport;
        this.beerImport = beerImport;
    }

    public void run(File importFile) {
        try {
            FileInputStream fis = new FileInputStream(importFile);
            Workbook workbook = new XSSFWorkbook(fis);
            this.beerImport.run(this.extractElements(workbook.getSheetAt(0), BeerImportExcelColumn.class));
            this.breweryImport.run(this.extractElements(workbook.getSheetAt(1), BreweryImportExcelColumn.class));
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

}
