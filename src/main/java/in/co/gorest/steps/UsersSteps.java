package in.co.gorest.steps;

import in.co.gorest.constants.EndPoints;
import in.co.gorest.model.PostsPojo;
import in.co.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UsersSteps {
    @Step("Creating user with name: {0}, gender: {1}, status: {2}, email: {3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status, String token) {
        UserPojo userPojo = UserPojo.getRequestBody(name, gender, email, status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Update user details with name: {0}, gender: {1}, userid: {2}, email: {3}, status: {4}")
    public ValidatableResponse updateUser(String name, String gender, int userID, String email, String status, String token) {
        UserPojo userPojo = UserPojo.getRequestBody(name, gender, email, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Get user info by userId: {0}")
    public ValidatableResponse getUserByID(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }

    @Step("Delete User with userId: {0}")
    public ValidatableResponse deleteUser(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting all Users information")
    public ValidatableResponse getAllUserInfo(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.CREATE_USER)
                .then();
    }
}
