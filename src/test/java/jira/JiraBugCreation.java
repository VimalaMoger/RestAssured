/*
package jira;

import com.moger.automation.util.JiraIssuePayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.moger.automation.util.KeyValues;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.*;


public class JiraBugCreation {

    // Headers
    Map<String, String> headers = new HashMap<>();

    // Authentication (API token)
    String apiToken = KeyValues.getGetApiToken();

    // Payload (issue details)
    String payload = JiraIssuePayload.payload();

    @BeforeAll
    public static void setup() {
        // Base URI
        RestAssured.baseURI = KeyValues.getBaseUri();
    }

    @Test
    public void testPostRequest() {

        // Headers
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Basic " + apiToken);


        // Send POST request
        Response response = given()
                //.log().all()
                .headers(headers)
                .body(payload)
                .when().post("rest/api/3/issue")
                .then()
                .log().all()
                .statusCode(201) // Assert status code
                .extract()
                .response();


        //Response validation - Get issue id
        String issueId = response.jsonPath().getString("id");


        /** POST request with attachment */
        Response res = given()
                //.log().all()
                .pathParam("key", issueId)
                .header("X-Atlassian-Token", "no-check")
                //.header("Content-Type", "multipart/form-data")
                .header("Authorization", "Basic " + apiToken)
                .multiPart(new File("assets/restassured.PNG"))
                .post("rest/api/3/issue/{key}/attachments")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();
    }


    //Post a new issue using external file
    @Test(dependsOnMethods = {"testPostRequest"}, enabled = false)
    public void testPostIssueRequest() throws IOException {

        // Headers
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Basic " + apiToken);

        // Reading static Json from an external file
        Response response = given()
                //.log().all()
                .headers(headers)
                .body(new String(Files.readAllBytes((Paths.get("buildIssue.json")))))
                .when().post("rest/api/3/issue")
                .then()
                //.log().all()
                .statusCode(201) // Assert status code
                .extract()
                .response();
    }

    //Get request
    @Test(dependsOnMethods = {"testPostIssueRequest"})
    public void testGetRequest() {

        String issueId = "10041";

        // Send GET request
        Response response = given()
                //.log().all()
                .pathParam("key", issueId)
                .header("Authorization", "Basic " + apiToken)
                .when().get("rest/api/3/issue/{key}")
                .then()
                //.log().all()
                .statusCode(200) // Assert status code
                .extract()
                .response();

        String description = response.jsonPath().getString("fields.issuetype.description");
        Assert.assertEquals(description, "Bugs track problems or errors.");

        String issueName = response.jsonPath().getString("fields.issuetype.name");
        System.out.println(issueName);
        Assert.assertEquals(issueName, "Bug");

        String projectName = response.jsonPath().getString("fields.project.name");
        Assert.assertEquals(projectName, "My Scrum Project");

        Assert.assertEquals(response.jsonPath().getString("key"), "SCRUM-7");
    }

    // Update existing issue
    @Test
    public void testUpdateRequest() {

        // Headers
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Basic " + apiToken);

        // Issue Key to update
        String issueKey = "SCRUM-6";

        // Send UPDATE request
        Response response = given()
                //.log().all()
                .headers(headers)
                .body(JiraIssuePayload.updatePayload())
                .when().put("rest/api/3/issue/" + issueKey)
                .then()
                //.log().all()
                .statusCode(204) // Assert status code
                .extract()
                .response();
    }

    //Delete an issue
    @Test(dataProvider = "key", dependsOnMethods = {"testUpdateRequest"})
    public void testDeleteRequest(String issueKey) {

        // Base URI
        RestAssured.baseURI = KeyValues.getBaseUri();

        // Headers
        headers.put("Content-Type", "application/json");

        headers.put("Authorization", "Basic " + apiToken);

        // Issue Key to update
        //String issueKey = "SCRUM-13";

        // Send DELETE request
        Response response = given()
                .log().all()
                .headers(headers)
                .body(JiraIssuePayload.updatePayload())
                .when().delete("rest/api/3/issue/" + issueKey)
                .then()
                .log().all()
                .statusCode(204) // Assert status code
                .extract()
                .response();
    }

    //Delete issues with set of issue keys
    @DataProvider(name = "key")
    public Object[][] returnValues() {
        return new Object[][]{{"SCRUM-53"}, {"SCRUM-52"}};
    }
}
*/
