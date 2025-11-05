package excelDrivenTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moger.automation.util.excel.ExcelReader;
import com.moger.automation.pojos.Book;
import com.moger.automation.util.BuildJson;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import specBuider.CreateSpecBuilder;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ExcelWithRestAssured {

    String excelPath = "book.xlsx";  // Path to Excel file

    String sheetName = "Book Data";

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDataFromExcel () throws JsonProcessingException, JSONException {

        Gson gson = new Gson();
        String book = gson.toJson(ExcelReader.readExcelFile(excelPath, sheetName).getFirst());

        Response response = given()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                .extract().response();

        String stringToParse = response.getBody().asString();


        Book actualBook = objectMapper.readValue(stringToParse, Book.class);

        Book expectedBook = gson.fromJson(BuildJson.buildJson(), Book.class);

        Assert.assertEquals(actualBook, expectedBook);
    }
}

