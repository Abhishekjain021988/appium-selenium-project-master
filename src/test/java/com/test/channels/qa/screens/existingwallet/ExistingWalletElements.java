package com.test.channels.qa.screens.existingwallet;

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
public class ExistingWalletElements extends BaseElements {

    @AndroidFindBy(xpath = "//*[contains(@text,'Most popular')]//" +
            "following-sibling::*[contains(@resource-id,'secretPhrase')]")
    private MobileElement secretPhrase;

    @AndroidFindBy(xpath = "//*[contains(@text,'Most popular')]//" +
            "following-sibling::*[contains(@resource-id,'privateKey')]")
    private MobileElement privateKey;

    @AndroidFindBy(xpath = "//*[contains(@text,'Other options')]//" +
            "following-sibling::*[contains(@resource-id,'googleDriveBackup')]")
    private MobileElement googleDriveBackup;

    @AndroidFindBy(xpath = "//*[contains(@text,'Other options')]//" +
            "following-sibling::*[contains(@resource-id,'viewOnlyWallet')]")
    private MobileElement viewOnlyWallet;

    @AndroidFindBy(xpath = "//*[contains(@text,'Other options')]//" +
            "following-sibling::*[contains(@resource-id,'keystore')]")
    private MobileElement keyStore;

    @AndroidFindBy(xpath = "//*[contains(@text,'Other options')]//" +
            "following-sibling::*[contains(@resource-id,'swiftOption')]")
    private MobileElement swift;
}
