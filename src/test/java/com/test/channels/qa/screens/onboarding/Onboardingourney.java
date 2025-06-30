package com.test.channels.qa.screens.onboarding;

import com.test.channels.qa.framework.components.BaseJourney;
import com.test.channels.qa.framework.components.Elements;
import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.text.MessageFormat;
import java.util.stream.IntStream;

/**
 * @author abhishekjain
 *
 */
@Log4j2
@SuppressWarnings("rawtypes")

@Elements(elements = OnboardingElements.class)
public class Onboardingourney extends BaseJourney {

    private String XPATH = "//*[contains(@text,'\"'{0}'\") or contains(@label,\"'{1}'\")]";

    /* ---------- Public helper methods used by the Steps ---------- */

    public boolean isDisplayed() {
        return this.getComponent("root") != null && this.getComponent("root").isDisplayed();
    }

    public String getHeadlineText(String headline) {
        MobileElement btn = (MobileElement) this.getDriver().findElement(By.xpath(MessageFormat.format(XPATH, headline, headline)));
        return btn.getText();
    }

    public boolean isPrimaryButtonEnabled(String label) {
        MobileElement btn = (MobileElement) this.getDriver().findElement(By.xpath(MessageFormat.format(XPATH, label, label)));
        return btn.isEnabled();
    }

    public boolean isSecondaryButtonStyledCorrectly(String label) {
        MobileElement btn = (MobileElement) this.getDriver().findElement(By.xpath(MessageFormat.format(XPATH, label, label)));
        // Simple style check – disabled & grey (alpha < 1).  Replace with your colour util.
        String enabledAttr = btn.getAttribute("enabled");
        String alphaAttr   = btn.getCssValue("alpha"); // works on Espresso; else skip
        boolean looksGrey  = alphaAttr == null || Double.parseDouble(alphaAttr) < 0.8;
        return ("false".equals(enabledAttr) || !btn.isEnabled()) && looksGrey;
    }

    public boolean legalLinksAreVisible(String link1, String link2) {
        String text = this.getComponent("legalText").getText().toLowerCase();
        return text.contains(link1.toLowerCase()) && text.contains(link2.toLowerCase());
    }

    public void clickOnButton(String text) {
        MobileElement btn = (MobileElement) this.getDriver().findElement(By.xpath(MessageFormat.format(XPATH, text, text)));
        btn.click();
    }

    public boolean labelIsVisible(String labelText) {
        MobileElement label = (MobileElement) this.getDriver().findElement(
                By.xpath(MessageFormat.format(XPATH, labelText, labelText)));
        return label.isDisplayed();
    }

    public void enterPasscode(String passcode) {
        IntStream.range(0, passcode.length()) .forEach(i ->
            {
                MobileElement label = (MobileElement) this.getDriver().findElement(
                        By.xpath(MessageFormat.format(XPATH, passcode.charAt(i), passcode.charAt(i))));
                label.click();
            }
        );
    }
}
