package com.moger.automation;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.moger.automation.util.BuildJson;

public class testApiRequest {

    @Test
    public void testPostRequest() {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJsonWithId(), Object.class);

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
            String title = response.jsonPath().getString("title");
            Assert.assertNotNull(title, "Walden");
            System.out.println("Title: " + title);

        }else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "400 BAD_REQUEST");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "All IDs updated automatically, id value in the request body must be zero!");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "MethodArgumentNotValidException");
        }

    }

    @Test
    public void testPostRequestNoIds() {
        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(BuildJson.buildJson(), Object.class);

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
            String title = response.jsonPath().getString("title");
            Assert.assertNotNull(title, "Walden");
            System.out.println("Title: " + title);
        }
    }
}

