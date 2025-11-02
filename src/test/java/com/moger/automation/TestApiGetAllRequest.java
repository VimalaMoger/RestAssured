package com.moger.automation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

        List<Object> list = response.jsonPath().getList("authors");
        //for(Object s : list)
        //    System.out.println(s);
        System.out.println(list.get(3));

        // Extract JSON array/object size
        int size = response.jsonPath().getList("id").size();

        // Validate the size
        //Assert.assertEquals(size, 1);

        Assert.assertEquals(response.getHeaders().getValue("content-type"), "application/json");
    }
}
