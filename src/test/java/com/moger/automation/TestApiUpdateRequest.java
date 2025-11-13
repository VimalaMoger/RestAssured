package com.moger.automation;

import com.google.gson.Gson;
import com.moger.automation.pojos.Book;
import com.moger.automation.util.UpdateJson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import specBuider.CreateSpecBuilder;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TestApiUpdateRequest {

    @Test (dataProvider = "rating")
    public void testUpdateRequestValidId(double val) throws FileNotFoundException {

        Gson gson = new Gson();
        Book book = gson.fromJson(UpdateJson.buildJson(val), Book.class);

        //Path variable
        Long id = 1L;

        // Send UPDATE request with path variable
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost().pathParam("id", id))   //Set path variable
                .body(book)
                .when().put("api/books/{id}")   //path variable in endpoint
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder()) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            String rating = response.jsonPath().getString("amazonRating");
            Assert.assertNotNull(rating, "5");

        } else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "400 BAD_REQUEST");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Rating value must be between 1 and 5 inclusive");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "MethodArgumentNotValidException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    @Test
    public void testUpdateRequestInvalidId() throws FileNotFoundException {

        Gson gson = new Gson();
        Book book = gson.fromJson(UpdateJson.buildJson(5), Book.class);

        //Path variable
        Long id = 11L;

        // Send UPDATE request with path variable
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost().pathParam("id", id))   //Set path variable
                .body(book)
                .when().put("api/books/{id}")//Used path variable in endpoint
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder()) // Assert status code
                .extract()
                .response();


        // Validate response body
        //if it is a new ID, it merges the data
        if (response.getContentType().contains("application/json")) {

            String rating = response.jsonPath().getString("amazonRating");
            Assert.assertNotNull(rating, "5");

        } else {
            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id 11 not found");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    @DataProvider(name = "rating")
    public Object[][] getData() {
        return new Object[][]{{5.0}, {3.5}, {2.0}, {0.7}};
    }
}
