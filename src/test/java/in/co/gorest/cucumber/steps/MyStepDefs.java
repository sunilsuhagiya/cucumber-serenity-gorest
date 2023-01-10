package in.co.gorest.cucumber.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.steps.UsersSteps;
import in.co.gorest.utils.PropertyReader;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

public class MyStepDefs {

    static ValidatableResponse response;
    static String token = PropertyReader.getInstance().getProperty("token");
    static String name = "Prime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = name + "@email.com";
    static String status = "active";
    static int userID;

    @Steps
    UsersSteps usersSteps;

    @When("^User sends a GET request to userID endpoint$")
    public void userSendsAGETRequestToUserIDEndpoint() {
        response = usersSteps.getAllUserInfo();
    }
    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }
    @When("^I create new user by providing the information$")
    public void iCreateNewUserByProvidingTheInformation() {
        response = usersSteps.createUser(name,gender,email,status,token);
    }

    @Then("^I must get a valid status code 201$")
    public void iMustGetAValidStatusCode() {
        response.statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @When("^I update new user by providing the information$")
    public void iUpdateNewUserByProvidingTheInformation() {
        name = "_updated";
        response = usersSteps.updateUser(name,gender,userID,email,status,token);
    }

    @When("^I delete a DELETE request by providing information$")
    public void iDeleteADELETERequestByProvidingInformation() {
        response = usersSteps.deleteUser(userID,token);

    }

    @Then("^I verify that the status code 204$")
    public void iVerifyThatTheStatusCode() {
        response.statusCode(204);

    }

    @Then("^I must get back a valid status code 200$")
    public void iMustGetBackAValidStatusCode() {
        response.statusCode(200).body("id", Matchers.equalTo(userID),
                "name", Matchers.equalTo(name), "email", Matchers.equalTo(email),
                "gender", Matchers.equalTo(gender), "status", Matchers.equalTo(status));
    }
}
