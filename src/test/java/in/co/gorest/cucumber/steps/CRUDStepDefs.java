package in.co.gorest.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.steps.UsersSteps;
import in.co.gorest.utils.PropertyReader;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class CRUDStepDefs {
    static ValidatableResponse response;
    static String token = PropertyReader.getInstance().getProperty("token");
    static int userID;
    static String name = null;

    @Steps
    UsersSteps usersSteps;

    @Given("^Gorest application is running$")
    public void gorestApplicationIsRunning() {
    }

    @When("^When I create a new user by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"token\"([^\"]*)\"$")
    public void whenICreateANewUserByProvidingTheInformationNameEmailGenderStatusToken(String name1, String email, String gender, String status, String _token) {
        name = name1 + TestUtils.getRandomValue();
        email = TestUtils.getRandomValue() + email;
        response = usersSteps.createUser(name, gender, email, status, token);

    }

    @Then("^I verify that the user with \"([^\"]*)\" is created$")
    public void iVerifyThatTheUserWithIsCreated(String name) {
        response.statusCode(201);
        userID = response.log().all().extract().body().path("id");
        System.out.println(userID);

    }

    @And("^I update the user with information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateTheUserWithInformationNameEmailGenderStatus(String name1, String email, String gender, String status) {
        name = name1 + "_abc";
        email = TestUtils.getRandomValue() + email;
        response = usersSteps.updateUser(name,gender,userID,email, status, token);
    }

    @And("^The user is updated successfully$")
    public void theUserIsUpdatedSuccessfully() {

    }

    @And("^I delete the user with userId$")
    public void iDeleteTheUserWithUserId() {
        response = usersSteps.deleteUser(userID,token);
    }

    @Then("^The user deleted successfully from the application$")
    public void theUserDeletedSuccessfullyFromTheApplication() {
        response.statusCode(204);
    }
}
