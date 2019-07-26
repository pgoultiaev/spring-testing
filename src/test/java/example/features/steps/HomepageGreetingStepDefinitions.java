package example.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.features.steps.serenity.UserSteps;
import net.thucydides.core.annotations.Steps;

public class HomepageGreetingStepDefinitions {
    @Steps
    UserSteps user;

    @Given("My last name is (.*)")
    public void userHasLastName(String name) {
        user.hasLastName(name);
    }

    @When("I visit the homepage")
    public void visitsHomepage() {
        user.visitsHomepage();
    }

    @Then("I should see (.*)")
    public void shouldSee(String greeting) {
        user.shouldSee(greeting);
    }
}