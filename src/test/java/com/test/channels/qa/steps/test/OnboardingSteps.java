package com.test.channels.qa.steps.test;

import com.test.channels.qa.screens.onboarding.Onboardingourney;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class OnboardingSteps {

    private Onboardingourney screen;

    public OnboardingSteps() {
        log.info("Initializing on-boarding step definitions");
        screen = new Onboardingourney();
    }

    /* ---------- GIVEN ---------- */

    @Given("the app is launched")
    public void theAppIsLaunched() {
        log.info("Checking onboarding screen visibility");
        assertThat(screen.isDisplayed()).isTrue();
    }

    /* ---------- THEN ---------- */

    @Then("I should see the headline text {string}")
    public void iShouldSeeTheHeadlineText(String expectedHeadline) {
        assertThat(screen.getHeadlineText(expectedHeadline)).isEqualToIgnoringCase(expectedHeadline);
    }

    @Then("I should see a {string} button enabled")
    public void iShouldSeeAButtonEnabled(String label) {
        assertThat(screen.isPrimaryButtonEnabled(label)).isTrue();
    }

    @Then("I should see an {string} button styled as secondary")
    public void iShouldSeeAnButtonStyledAsSecondary(String label) {
        assertThat(screen.isSecondaryButtonStyledCorrectly(label)).isTrue();
    }

    @Then("I should see legal text with links {string} and {string}")
    public void iShouldSeeLegalTextWithLinksAnd(String link1, String link2) {
        assertThat(screen.legalLinksAreVisible(link1, link2)).isTrue();
    }


    @When("I click on {string} button")
    public void iClickOnButton(String text) {
        screen.clickOnButton(text);
    }

    @And("I should see a {string} label")
    public void iShouldSeeALabel(String label) {
        assertThat(screen.labelIsVisible(label)).isTrue();
    }

    @And("I enter {string} passcode")
    public void iEnterPasscode(String passcode) {
        screen.enterPasscode(passcode);
    }

    @When("I click on go back")
    public void iClickOnGoBack() {
        screen.goBack();
    }
}
