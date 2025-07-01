package com.test.channels.qa.screens.onboarding;

import com.test.channels.qa.framework.components.BaseElements;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;

/**
 * @author abhishekjain
 *
 */

@SuppressWarnings("rawtypes")
@Getter
public class OnboardingElements extends BaseElements {

    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/onboarding_root")
    private MobileElement root;

    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/headline_text")
    private MobileElement headline;

    @AndroidFindBy(xpath = "//android.widget.TextView[2]")
    private MobileElement legalText;
}
