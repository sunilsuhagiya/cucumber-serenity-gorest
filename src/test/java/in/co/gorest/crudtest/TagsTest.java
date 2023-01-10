package in.co.gorest.crudtest;

import in.co.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {
    @WithTags({
            @WithTag("userfeature:NEGATIVE"),
            @WithTag("userfeature:SMOKE")
    })
    @Title("Provide a 404 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .patch("/users")
                .then()
                .statusCode(404)
                .log().all();
    }

    @WithTags({
            @WithTag("userfeature:POSITIVE"),
            @WithTag("userfeature:SMOKE")
    })
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().all();

    }

}
