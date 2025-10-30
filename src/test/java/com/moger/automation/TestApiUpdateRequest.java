package com.moger.automation;

import com.google.gson.Gson;
import com.moger.automation.util.UpdateJson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestApiUpdateRequest {

    @Test
    public void testUpdateRequestValidId() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(UpdateJson.buildJson(), Object.class);

        //Path variable
        Long id = 1L;

        // Send UPDATE request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .header("Content-Type", "application/json")  //Set header
                .body(book)
                .when().put("api/books/{id}")   //path variable in endpoint
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            String rating = response.jsonPath().getString("amazonRating");
            Assert.assertNotNull(rating, "5");

        } else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id 1 not found");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    @Test
    public void testUpdateRequestInvalidId() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(UpdateJson.buildJson(), Object.class);

        //Path variable
        Long id = 11L;

        // Send UPDATE request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .header("Content-Type", "application/json")  //Set header
                .body(book)
                .when().put("api/books/{id}")//Used path variable in endpoint
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
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
}
