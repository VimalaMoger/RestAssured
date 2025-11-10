package stepDefinition;

import com.google.gson.Gson;
import com.moger.automation.pojos.Book;
import com.moger.automation.util.BuildJson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import specBuider.CreateSpecBuilder;

import java.io.FileNotFoundException;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    Response response;
    RequestSpecification req;
    Book book;

    //Path variable
    private final Long id = 1L;


    //Post request
    @Given("Add Book payload")
    public void add_book_payload() throws FileNotFoundException {

        Gson gson = new Gson();
        book = gson.fromJson(BuildJson.buildJson(), Book.class);

        req = given()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book);
    }

    @When("Send a POST HTTP request")
    public void send_a_post_http_request() {

        response = req.when().post("api/books");
    }

    //Update request
    @Given("Add Update Book payload and Id")
    public void update_book_payload() throws FileNotFoundException {

        Gson gson = new Gson();
        book = gson.fromJson(BuildJson.buildJson(), Book.class);

        req = given()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book);
    }

    @When("Send a PUT HTTP request")
    public void send_a_put_http_request() {

        response = req.pathParam("id", id).when().put("api/books/{id}");
    }

    //Given input for Get n Delete request
    @Given("Send Book with {int}")
    public void send_book_id(Integer id) throws FileNotFoundException {

        req = given().pathParam("id", id).spec(CreateSpecBuilder.getRequestSpecBuilderPost());
    }

    @When("Send a GET HTTP request with {int}")
    public void send_a_get_http_request_with_id(Integer id) {

        response = req.when().get("api/books/{id}");
    }

    //Delete request
    @When("Send a DELETE HTTP request with {int}")
    public void send_a_delete_http_request(Integer id) {

        response = req.pathParam("id", id).when().delete("api/books/{id}");
    }


    //reusable for all requests
    @Then("Receive a success response with status code {string}")
    public void receive_a_success_response_with_status_code(String statusCode) {

        response = response.then()
                .spec(CreateSpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {

        if (key.equalsIgnoreCase("title"))
            assertEquals(response.jsonPath().getString(key), value);
        if (key.equalsIgnoreCase("message"))
            //assertEquals(response.getBody().asString(), value);
            Assert.assertEquals(response.getBody().asString().contains(value), true);
    }
}
