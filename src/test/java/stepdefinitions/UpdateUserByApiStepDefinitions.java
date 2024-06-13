package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.User;
import pojos.UserPojo;
import utilities.ObjectMapperUtils;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.CreateUserBySeleniumStepDefinitions.email;
import static stepdefinitions.CreateUserBySeleniumStepDefinitions.password;


public class UpdateUserByApiStepDefinitions {
    UserPojo expectedData;
    Response response;

    @Given("set the url for patch request")
    public void setTheUrlForPatchRequest() {
        //https://thinking-tester-contact-list.herokuapp.com/users/me
        spec.pathParams("first", "users", "second", "me");
    }

    @And("set the expected data for patch request")
    public void setTheExpectedDataForPatchRequest() {
        String json = """
                {
                  "firstName": "Tom",
                  "lastName": "Cook",
                  "email": "test@fake.com",
                  "password": "Tom.123"
                }
                """;
        expectedData = ObjectMapperUtils.jsonToJava(json, UserPojo.class);
        System.out.println("expectedData = " + expectedData);
        expectedData.setEmail(Faker.instance().internet().emailAddress()); //"test@fake.com", olarak belirtilen sabit email'e, Faker dan gelen yeni mail assign ediliyor.
        email=expectedData.getEmail(); //Yeni mail, eski mail'in üzerine yaziliyor.
        password = expectedData.getPassword(); //Yeni password, eski password'ün üzerine yaziliyor.

    }

    @When("send the patch request and get the response")
    public void sendThePatchRequestAndGetTheResponse() {
        response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

    }

    @Then("do assertion for patch request")
    public void doAssertionForPatchRequest() {
        User actualData = response.as(User.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());


    }


}
