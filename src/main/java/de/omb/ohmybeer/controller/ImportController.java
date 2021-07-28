package de.omb.ohmybeer.controller;

import de.omb.ohmybeer.dataimport.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/import", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImportController {

    private final ExcelImport excelImport;

    @Autowired
    public ImportController(ExcelImport excelImport) {
        this.excelImport = excelImport;
    }

    @PostMapping
    public void startImport() {
        excelImport.start();

    }
}
