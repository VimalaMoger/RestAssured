package com.moger.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class TestApiDeleteRequest {

    @Test
    public void testDeleteRequest() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        //Path variable
        Long id = 1L;

        // Send DELETE request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .when().delete("api/books/{id}") // path variable in endpoint
                .then()
                //.log().all()
                .statusCode(HttpStatus.OK.value()) // Assert status code.
                .extract()
                .response();

        // Validate response body
        if (response.getContentType().contains("application/json")) {

            Assert.assertEquals(response.getBody().asString().contains("Book with id 1 is deleted"), true);
        } else {

            Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
            System.out.println(response.contentType());
            Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id 1 is not found");
            Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
            Assert.assertEquals(response.getHeaders().getValue("server"), "cloudflare");
        }
}

    //With invalid ID
    @Test
    public void testDeleteRequestInvalidId() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        //Path variable
        Long id = -1L;

        // Send DELETE request with path variable
        Response response = given()
                //.log().all()
                .pathParam("id", id)  //Set path variable
                .when().delete("api/books/{id}")  //path variable in endpoint
                .then()
                //.log().all()
                .statusCode(HttpStatus.OK.value()) // Assert status code.
                .extract()
                .response();

        // Validate response body
        Assert.assertEquals(response.contentType(), "text/html;charset=UTF-8");
        System.out.println(response.contentType());
        Assert.assertEquals(response.htmlPath().getString("html.body.p[0].span"), "404 NOT_FOUND");
        Assert.assertEquals(response.htmlPath().getString("html.body.p[1].span"), "Book with id -1 is not found");
        Assert.assertEquals(response.htmlPath().getString("html.body.p[2].span"), "BookNotFoundException");
    }
}
