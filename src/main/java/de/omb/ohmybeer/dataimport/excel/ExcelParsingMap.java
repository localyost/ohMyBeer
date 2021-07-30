package de.omb.ohmybeer.dataimport.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.Optional;

public class ExcelParsingMap extends HashMap<ExcelColumn, Optional<Object>> {

    public void addElement(ExcelColumn column, Row row) {
        Cell cell = row.getCell(column.ordinal());
        Object cellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = row.getCell(column.ordinal()).getStringCellValue();
                    break;
                case NUMERIC:
                    cellValue = row.getCell(column.ordinal()).getNumericCellValue();
                    break;
            }
        }
        this.put(column, Optional.ofNullable(cellValue));
    }
}
