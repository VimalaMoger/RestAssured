package excelDrivenTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moger.automation.util.excel.ExcelReader;
import com.moger.automation.pojos.Book;
import com.moger.automation.util.BuildJson;
import com.moger.automation.util.excel.ExcelWriter;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import specBuider.CreateSpecBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;
import static io.restassured.RestAssured.given;

public class ExcelWithRestAssured {

    ObjectMapper objectMapper = new ObjectMapper();
    static Book book;

    @BeforeAll
    public static void setUp () throws IOException, JSONException {

        String excelPath = "book.xlsx";  // Path to Excel file

        String sheetName = "Book Data";

        //Write to file
        ExcelWriter.createBooksInExcel();

        //Read from file
        book = GetBook.getBook(ExcelReader.readExcelFile(excelPath, sheetName).getFirst());
    }

    @Test
    public void testDataFromExcel () throws JsonProcessingException, FileNotFoundException {

        Response response = given()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                .extract().response();

        String stringToParse = response.getBody().asString();

        Book actualBook = objectMapper.readValue(stringToParse, Book.class);

        Gson gson = new Gson();

        Book expectedBook = gson.fromJson(BuildJson.buildJson(), Book.class);
        actualBook.setId(expectedBook.getId());

        //Functional approach  set all ids' to zero
        IntStream.range(0, Math.min(actualBook.getPublishers().size(), expectedBook.getPublishers().size()))
                .forEach(i -> {
                    actualBook.getPublishers().get(i).setId(expectedBook.getPublishers().get(i).getId());
                    actualBook.getAuthors().get(i).setId(expectedBook.getAuthors().get(i).getId());
                });

        Assert.assertEquals(actualBook, expectedBook);
    }
}

