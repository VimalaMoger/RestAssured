package com.moger.automation.util.excel;

import com.moger.automation.pojos.Author;
import com.moger.automation.pojos.Book;
import com.moger.automation.pojos.Publisher;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelWriter {

    public static void createBooksInExcel() throws IOException {

        List<Book> books = Arrays.asList(
                new Book(0, "Beginning Python", "https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg", 2010, Arrays.asList(new Publisher(0, "James Payne")), Arrays.asList(new Author(0,"Wiley Publishing, Inc")), Book.BookGenre.TECHNICAL, 4.3),
                new Book(0, "Self-Reliance and Other Essays", "https://images.gr-assets.com/books/1520778510l/123845.jpg", 1993, Arrays.asList(new Publisher(0,"")), Arrays.asList(new Author(0,"")), Book.BookGenre.TECHNICAL, 4.5)
        );

        // Create Workbook and Sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Book Data");

        // Create Header Row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Title");
        headerRow.createCell(2).setCellValue("Image_url");
        headerRow.createCell(3).setCellValue("PublicationYear");
        headerRow.createCell(4).setCellValue("Publishers");
        headerRow.createCell(5).setCellValue("Authors");
        headerRow.createCell(6).setCellValue("Genre");
        headerRow.createCell(7).setCellValue("AmazonRating");

        // Populate Data Rows
        int rowIndex = 1;
        for (Book book : books) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(book.getId());
            row.createCell(1).setCellValue(book.getTitle());
            row.createCell(2).setCellValue(book.getImage_url());
            row.createCell(3).setCellValue(book.getPublicationYear());
            row.createCell(4).setCellValue(book.getPublishers().getFirst().getName());
            row.createCell(5).setCellValue(book.getAuthors().getFirst().getName());
            row.createCell(6).setCellValue(book.getGenre().ordinal());
            row.createCell(7).setCellValue(book.getAmazonRating());
        }

        // Write to File
        try (FileOutputStream fileOut = new FileOutputStream("book.xlsx")) {
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        workbook.close();
        System.out.println("Excel file created successfully!");
    }

    static void main() throws IOException {
        createBooksInExcel();
    }
}
