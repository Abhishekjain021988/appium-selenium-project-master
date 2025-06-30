package com.test.channels.qa.steps.test;

import com.test.channels.qa.screens.existingwallet.ExistingWalletJourney;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class AddExistingWalletSteps {

    private ExistingWalletJourney screen;

    public AddExistingWalletSteps() {
        log.info("Initializing existing wallet step definitions");
        screen = new ExistingWalletJourney();
    }

    /* ---------- GIVEN ---------- */



    /* ---------- THEN ---------- */

    /* ---------- AND ---------- */

    @And("I should see secret phrase label below most popular section")
    public void iShouldSeeSecretPhraseLabelBelowMostPopularSection() {
        assertThat(screen.secretPhaseIsVisible()).isTrue();
    }

    @And("I should see private key label below most popular section")
    public void iShouldSeePrivateKeyLabelBelowMostPopularSection() {
        assertThat(screen.privateKeyIsVisible()).isTrue();
    }


    @And("I should see google drive backup label below other options section")
    public void iShouldSeeGoogleDriveBackupLabelBelowOtherOptionsSection() {
        assertThat(screen.googleDriveBackupIsVisible()).isTrue();
    }

    @And("I should see keystore label below other options section")
    public void iShouldSeeKeystoreLabelBelowOtherOptionsSection() {
        assertThat(screen.keyStoreIsVisible()).isTrue();
    }

    @And("I should see view only wallet label below other options section")
    public void iShouldSeeViewOnlyWalletLabelBelowOtherOptionsSection() {
        assertThat(screen.viewOnlyWalletIsVisible()).isTrue();
    }

    @And("I should see swift label below other options section")
    public void iShouldSeeSwiftLabelBelowOtherOptionsSection() {
        assertThat(screen.swiftIsVisible()).isTrue();
    }
}
