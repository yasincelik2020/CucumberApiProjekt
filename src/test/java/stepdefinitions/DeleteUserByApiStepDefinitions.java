package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.User;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteUserByApiStepDefinitions {
    Response response;

    @Given("set the url for delete request")
    public void setTheUrlForDeleteRequest() {
        //https://thinking-tester-contact-list.herokuapp.com/users/me
        spec.pathParams("first", "users", "second", "me");
    }

    @When("send the delete request and get the response")
    public void sendTheDeleteRequestAndGetTheResponse() {
        response = given(spec).delete("{first}/{second}");
       // response.prettyPrint();
    }

    @Then("do assertion for delete request")
    public void doAssertionForDeleteRequest() {
        assertEquals(200, response.statusCode());
    }

    @And("body should be empty")
    public void bodyShouldBeEmpty() {
        assertTrue(response.asString().isEmpty());
    }
}
