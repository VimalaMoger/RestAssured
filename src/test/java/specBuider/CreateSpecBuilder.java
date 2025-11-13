package specBuider;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CreateSpecBuilder {

    //Create RequestSpecBuilder instance
    static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    //Create ResponseSpecBuilder instance
    static ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


    public static RequestSpecification getRequestSpecBuilderPost () throws FileNotFoundException {

        PrintStream file = new PrintStream(new FileOutputStream("logging.txt"));

        //Add configuration
        requestSpecBuilder.setBaseUri("https://book-v9.onrender.com");    // Base URI
        requestSpecBuilder.addHeader("Content-Type", "application/json");  //Set header

        requestSpecBuilder.addFilter(RequestLoggingFilter.logRequestTo(file));
        requestSpecBuilder.addFilter(ResponseLoggingFilter.logResponseTo(file));

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
