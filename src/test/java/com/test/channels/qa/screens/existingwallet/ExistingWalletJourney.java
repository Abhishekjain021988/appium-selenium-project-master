package com.test.channels.qa.screens.existingwallet;

import com.test.channels.qa.framework.components.BaseJourney;
import com.test.channels.qa.framework.components.Elements;
import lombok.extern.log4j.Log4j2;

/**
 * @author abhishekjain
 *
 */
@Log4j2
@SuppressWarnings("rawtypes")

@Elements(elements = ExistingWalletElements.class)
public class ExistingWalletJourney extends BaseJourney {

    private String XPATH = "//*[contains(@text,'\"'{0}'\") or contains(@label,\"'{1}'\")]";

    /* ---------- Public helper methods used by the Steps ---------- */

    public boolean secretPhaseIsVisible() {
        return this.getComponent("secretPhrase").isDisplayed();
    }

    public boolean privateKeyIsVisible() {
        return this.getComponent("privateKey").isDisplayed();
    }

    public boolean googleDriveBackupIsVisible() {
        return this.getComponent("googleDriveBackup").isDisplayed();
    }

    public boolean viewOnlyWalletIsVisible() {
        return this.getComponent("viewOnlyWallet").isDisplayed();
    }

    public boolean keyStoreIsVisible() {
        return this.getComponent("keyStore").isDisplayed();
    }

    public boolean swiftIsVisible() {
        return this.getComponent("swift").isDisplayed();
    }
}
