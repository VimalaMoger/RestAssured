package com.moger.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestApiGetAllRequest {

    @Test
    public void testGetAllRequest() {

        // Base URI
        RestAssured.baseURI = "https://book-v9.onrender.com";

        // Send GET all request
        Response response = given()
                //.log().all()
                .when().get("api/books")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        // Validate response body

        // Extract JSON array/object size
        int size = response.jsonPath().getList("id").size();
        System.out.println(size);

        // Validate the size
        //Assert.assertEquals(size, 1);

        Assert.assertEquals(response.getHeaders().getValue("content-type"), "application/json");
    }
}
