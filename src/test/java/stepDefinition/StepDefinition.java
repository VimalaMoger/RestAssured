package stepDefinition;

import com.google.gson.Gson;
import com.moger.automation.pojos.Book;
import com.moger.automation.util.BuildJson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import specBuider.CreateSpecBuilder;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    Response response;
    RequestSpecification req;
    Book book;

    @Given("book payload")
    public void book_payload() throws FileNotFoundException {

        Gson gson = new Gson();
        book = gson.fromJson(BuildJson.buildJson(), Book.class);

        req = given()
                .spec(CreateSpecBuilder.getRequestSpecBuilderPost())
                .body(book);
    }

    @When("user requests AddBook API with Post http request")
    public void user_requests_add_book_api_with_post_http_request() {

        response = req.when().post("api/books");
    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {

        response = response.then()
                .spec(CreateSpecBuilder.getResponseSpecBuilder()) // Assert status code
                .extract()
                .response();
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {

        assertEquals(response.jsonPath().getString(key), value);
    }

}
