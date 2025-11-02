package com.moger.automation;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.moger.automation.util.BuildJson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApiPostRequest {

    @Test
    public void testPostRequest() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJsonWithId(), Object.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .header("Content-Type", "application/json")
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
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

    //Post a new request using external file
    @Test
    public void testPostRequestNoIds() throws IOException {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        // Send POST request
        // Reading static Json from an external file
        Response response = given()
                //.log().all()
                .headers("Content-Type", "application/json")
                .body(new String(Files.readAllBytes((Paths.get("C:\\Users\\mvmgr\\Documents\\automation\\buildBook.json")))))
                .when().post("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {
            String title = response.jsonPath().getString("title");
            Assert.assertNotNull(title, "James Payne");
            System.out.println("Title: " + title);
        }
    }

    //Empty title
    @Test
    public void testPostRequestEmptyTitle() {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJsonEmptyTitle(), Object.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .headers("Content-Type", "application/json")
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
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
    public void testPostRequestRatingInvalid() {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJsonRatingInvalid(), Object.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .header("Content-Type", "application/json")
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
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
    public void testPostRequestEmptyField() {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJsonForAnyFieldEmpty(), Object.class);

        // Send POST request
        Response response = given()
                //.log().all()
                .headers("Content-Type", "application/json")
                .body(book)
                .when().post("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {
            String detail = response.jsonPath().getString("detail");
            Assert.assertEquals(detail, "Failed to read request");
        }
    }
}

