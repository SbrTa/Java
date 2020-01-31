package com.example.image.service;

import com.example.image.model.ExcelColumnDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author SbrTa
 * @since 1/31/2020  3:20 PM
 */

@Service
public class DataImportExportService {


    private static final String FILE_NAME = "classpath:MovieGallery.xlsx";

    public void readFromExcel() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void exportAsExcel(HttpServletResponse response, String fileName, List<ExcelColumnDetails> columns, List<?> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        int rowCount = 0;
        int columnCount = 0;
        Row row = sheet.createRow(rowCount++);
        for (ExcelColumnDetails columnDetails : columns) {
            row.createCell(columnCount++).setCellValue(columnDetails.getHeader());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object obj : data) {
            row = sheet.createRow(rowCount++);
            Map<String, Object> rowData = objectMapper.convertValue(obj, Map.class);
            columnCount = 0;
            for (ExcelColumnDetails columnDetails : columns) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue((String) rowData.get(columnDetails.getParameter()));
            }
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}