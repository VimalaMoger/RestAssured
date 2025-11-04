package specBuider;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CreateSpecBuilder {

    //Create RequestSpecBuilder instance
    static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    //Create ResponseSpecBuilder instance
    static ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


    public static RequestSpecification getRequestSpecBuilderPost () {

        //Add configuration
        requestSpecBuilder.setBaseUri("https://book-v9.onrender.com");    // Base URI
        requestSpecBuilder.addHeader("Content-Type", "application/json");  //Set header
        //requestSpecBuilder.setContentType("application/json");

        //Build the RequestSpecification
        return requestSpecBuilder.build();
    }

    public static ResponseSpecification getResponseSpecBuilder () {

        //Add configuration
        responseSpecBuilder.expectStatusCode(200);
        //responseSpecBuilder.expectContentType("application/json");

        //Build the ResponseSpecification
        return responseSpecBuilder.build();
    }

}
