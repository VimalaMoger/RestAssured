package com.moger.automation;

import com.google.gson.Gson;
import com.moger.automation.pojos.Book;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.moger.automation.util.BuildJson;
import specBuider.CreateSpecBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApiPostRequest {

    @Test
    public void testPostRequest() throws FileNotFoundException {


        Gson gson = new Gson();
        Book book = gson.fromJson(BuildJson.buildJsonWithId(), Book.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder())// Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            String title = response.jsonPath().getString("title");
            Assert.assertNotNull(title, "Walden");
            System.out.println("Title: " + title);



        } else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "400 BAD_REQUEST");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "All IDs updated automatically, id value in the request body must be zero!");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "MethodArgumentNotValidException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    @Test
    public void testPostRequestValidData() throws FileNotFoundException {


        Gson gson = new Gson();
        Book book = gson.fromJson(BuildJson.buildJson(), Book.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder())// Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            String title = response.jsonPath().getString("title");
            Assert.assertNotNull(title, "Walden");
            System.out.println("Title: " + title);
        }
    }

    //Post a new request using external file
    @Test
    public void testPostRequestNoIds() throws IOException {

        // Send POST request
        // Reading static Json from an external file
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(new String(Files.readAllBytes((Paths.get("buildBook.json")))))
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {
            String title = response.jsonPath().getString("title");
            Assert.assertEquals(title, "Beginning Python");

            System.out.println("Title: " + title);
        }

        Book book = response.as(Book.class);
        Assert.assertEquals(book.getAuthors().get(0).getName(), "James Payne");
    }

    //Empty title
    @Test
    public void testPostRequestEmptyTitle() throws FileNotFoundException {

        Gson gson = new Gson();
        Book book = gson.fromJson(BuildJson.buildJsonEmptyTitle(), Book.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("text/html;charset=UTF-8")) {
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "400 BAD_REQUEST");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Title must not be empty");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "MethodArgumentNotValidException");
        }
    }

    //Rating Invalid
    @Test
    public void testPostRequestRatingInvalid() throws FileNotFoundException {

        Gson gson = new Gson();
        Book book = gson.fromJson(BuildJson.buildJsonRatingInvalid(), Book.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder()) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("text/html;charset=UTF-8")) {
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "400 BAD_REQUEST");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Rating value must be between 1 and 5 inclusive");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "MethodArgumentNotValidException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    //Author name is missing value
    @Test
    public void testPostRequestEmptyField() throws FileNotFoundException {

        Gson gson = new Gson();
        Book book = gson.fromJson(BuildJson.buildJsonForAnyFieldEmpty(), Book.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .spec(CreateSpecBuilder.getResponseSpecBuilder()) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {
            String detail = response.jsonPath().getString("detail");
            Assert.assertEquals(detail, "Failed to read request");
        }
    }
}

