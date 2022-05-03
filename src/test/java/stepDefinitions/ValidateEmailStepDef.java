
package stepDefinitions;

import apiServices.ValidateEmailService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pojo.Comments;

import java.util.ArrayList;
import java.util.List;

// Step class generated for validate_email feature file
//method name defines the step itself
public class ValidateEmailStepDef {
    ValidateEmailService validateEmailService = new ValidateEmailService();
    List<String> emails = new ArrayList<>();

    @Given("User search for the user {string}")
    public void userSearchForTheUser(String username) {
        validateEmailService.getUserDetailsByUsername(username);
    }

    @And("User fetch the user Id")
    public void userFetchTheUserId() {
        validateEmailService.getUserId();
    }

    @And("User fetch the posts written by the user")
    public void userFetchThePostsWrittenByTheUser() {
        validateEmailService.getPostsByUser();
    }

    @And("User fetch the post Ids for each post")
    public void userFetchThePostIdsForEachPost() {
        validateEmailService.extractPostIds();
    }

    @When("User fetch the comments for each post")
    public void userFetchTheCommentsForEachPost() {
        validateEmailService.getPostCommentEmailDetails();
    }

    @Then("User validate the email in the comment are valid emails")
    public void userValidateTheEmailInTheCommentAreValidEmails() {
        emails =validateEmailService.getEmails();
        for (String email : emails) {
            Assert.assertTrue(validateEmailService.validateEmail(email),"Validate email: "+email);
        }

    }



}