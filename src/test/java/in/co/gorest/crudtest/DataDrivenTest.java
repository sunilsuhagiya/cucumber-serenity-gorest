package in.co.gorest.crudtest;


import in.co.gorest.steps.UsersSteps;
import in.co.gorest.testbase.TestBase;
import in.co.gorest.utils.PropertyReader;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@UseTestDataFrom("src/test/java/resources/testdata/userdata.csv")
@RunWith(SerenityParameterizedRunner.class)
public class DataDrivenTest extends TestBase {
    static String token = PropertyReader.getInstance().getProperty("token");
    private String name;
    private String gender;
    private String email;
    private String status;

    @Steps
    UsersSteps usersSteps;

    @Title("Data driven test for adding multiple users")
    @Test
    public void createMultipleUsers() {
        usersSteps.createUser(name, gender, email, status,token).statusCode(201);
    }
}
