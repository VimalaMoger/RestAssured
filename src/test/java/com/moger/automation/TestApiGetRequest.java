package com.moger.automation;

import com.google.gson.Gson;
import com.moger.automation.util.UpdateJson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestApiGetRequest {

    @Test
    public void testGetRequestValidId() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";


        //Path variable
        Long id = 4L;

        // Send GET request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .when().get("api/books/{id}")//path variable in endpoint
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            String authorName = response.jsonPath().getString("authors[0].name");
            Assert.assertEquals(authorName, "Eric Freeman");

            String publisherName = response.jsonPath().getString("publishers[0].name");
            System.out.println(publisherName);
            Assert.assertEquals(publisherName, "O'Reilly Media");

            Assert.assertEquals(response.jsonPath().getString("genre"), "TECHNICAL");
            Assert.assertEquals(response.getHeaders().getValue("content-type"), "application/json");
        }
        else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id 4 not found");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
    }

    //Invalid ID
    @Test
    public void testGetRequestInvalidId() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        Gson gson = new Gson();
        Object book = gson.fromJson(UpdateJson.buildJson(), Object.class);

        //Path variable
        Long id = -2L;

        // Send GET request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .when().get("api/books/{id}")  //path variable in endpoint
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body
        Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
        System.out.println(response.contentType());
        Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
        Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id -2 not found");
        Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
        Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
    }
}
