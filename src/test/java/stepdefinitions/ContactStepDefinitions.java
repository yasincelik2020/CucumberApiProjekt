package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.ContactPojo;
import utilities.ObjectMapperUtils;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ContactStepDefinitions {


    ContactPojo expectedData;
    Response response;

    @Given("set the url for add user")
    public void set_the_url_for_add_user() {
        spec.pathParams("first","contacts");

    }
    @Given("set the expected data for add user")
    public void set_the_expected_dat_for_add_user() {
        String json = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }
                """;
        expectedData = ObjectMapperUtils.jsonToJava(json, ContactPojo.class);
        expectedData.setFirstName(Faker.instance().name().firstName());
        expectedData.setLastName(Faker.instance().name().lastName());
        expectedData.setEmail(Faker.instance().internet().emailAddress());
        System.out.println("expectedData = " + expectedData);

    }
    @When("send the post request and get the response")
    public void send_the_post_request_and_get_the_response() {
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

    }
    @Then("do assertion for add user")
    public void do_assertion_for_add_user() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getCountry(),actualData.getCountry());
        assertEquals(expectedData.getBirthdate(),actualData.getBirthdate());
        assertEquals(expectedData.getPhone(),actualData.getPhone());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getPostalCode(),actualData.getPostalCode());
        assertEquals(expectedData.getStateProvince(),actualData.getStateProvince());
        assertEquals(expectedData.getStreet1(),actualData.getStreet1());
        assertEquals(expectedData.getStreet2(),actualData.getStreet2());
    }

}
