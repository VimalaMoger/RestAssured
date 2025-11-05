package com.moger.automation.util.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static List<List<String>> readExcelFile (String excelPath, String sheetName) {

        List<List<String>> list = null;

        Row row = null;

        try (FileInputStream fis = new FileInputStream(new File(excelPath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Get the first sheet
            Sheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();
            list = new ArrayList<>();
            List<String> newList;

            //skip header row
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {

                row = rowIterator.next();

                newList = new ArrayList();

                // Iterate through cells in each row
                for (Cell cell : row) {

                    // Handle different cell types
                    newList.add(formatter.formatCellValue(cell));
                }
                // Move to the next row
                //newList.stream().forEach(System.out::print);
                list.add(newList);
            }
           // list.stream().map(String::valueOf).collect(toList()).stream().forEach(System.out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    static void main() {

        String excelPath = "book.xlsx";  // Path to Excel file

        String sheetName = "Book Data";
        System.out.println(readExcelFile(excelPath, sheetName).getFirst());
    }
}
