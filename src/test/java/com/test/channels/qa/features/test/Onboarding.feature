@onboarding

Feature: On-boarding in the trust crypto wallet

  @onboarding
  Scenario: Verify on-boarding screen elements
    And I should see a "Create new wallet" button enabled
    And I should see a "I already have a wallet" button enabled
    And I should see legal text with links "Terms of Service" and "Privacy Policy"

    @onboarding
    Scenario: Verify create a new wallet journey
      When I click on "Create new wallet" button
      Then I should see a "Create passcode" label
      And I should see a "Enter your passcode. Be sure to remember it so you can unlock your wallet." label
      When I enter "123456" passcode
      Then I should see a "Confirm passcode" label
      When I enter "123456" passcode
      Then I should see a "Enable Notifications" button enabled
      And I should see a "Skip, I'll do it later" button enabled
      And I should see a "Keep up with the market!" label
      And I should see a "Turn on notifications to keep track of prices and receive transaction updates." label
      When I click on "Enable Notifications" button
      Then I should see a "Buy Crypto" button enabled
      And I should see a "Deposit Crypto" button enabled
      And I should see a "Skip, I'll do it later" button enabled
      And I should see a "Brilliant, your wallet is ready!" label
      And I should see a "Buy or deposit to get started." label

  @onboarding
  Scenario: Verify back button on notifications page
    When I click on "Create new wallet" button
    And I enter "123456" passcode
    And I enter "123456" passcode
    Then I should see a "Enable Notifications" button enabled
    When I click on go back
    Then I should see a "Create new wallet" button enabled
    And I should see a "I already have a wallet" button enabled
    And I should see legal text with links "Terms of Service" and "Privacy Policy"

  @onboarding
  Scenario: Verify back button on confirm passcode page
    When I click on "Create new wallet" button
    And I enter "123456" passcode
    And I enter "123456" passcode
    When I click on go back
    Then I should see a "Create new wallet" button enabled
    And I should see a "I already have a wallet" button enabled
    And I should see legal text with links "Terms of Service" and "Privacy Policy"

  @onboarding
  Scenario: Verify back button on enter passcode page
    When I click on "Create new wallet" button
    And I enter "123456" passcode
    When I click on go back
    Then I should see a "Create new wallet" button enabled
    And I should see a "I already have a wallet" button enabled
    And I should see legal text with links "Terms of Service" and "Privacy Policy"

  @onboarding
  Scenario: Verify user will not be able to proceed with mismatch in passcode
    When I click on "Create new wallet" button
    Then I should see a "Create passcode" label
    And I should see a "Enter your passcode. Be sure to remember it so you can unlock your wallet." label
    When I enter "123456" passcode
    Then I should see a "Confirm passcode" label
    When I enter "123457" passcode
    Then I should see a "Those passwords" label
    Then I should see a "Create passcode" label
    When I enter "123457" passcode
    Then I should see a "Re-enter your passcode. Be sure to remember it so you can unlock your wallet" label
    When I enter "123457" passcode
    Then I should see a "Enable Notifications" button enabled
    And I should see a "Skip, I'll do it later" button enabled
    And I should see a "Keep up with the market!" label
    And I should see a "Turn on notifications to keep track of prices and receive transaction updates." label
    When I click on "Enable Notifications" button
    Then I should see a "Buy Crypto" button enabled
    And I should see a "Deposit Crypto" button enabled
    And I should see a "Skip, I'll do it later" button enabled
    And I should see a "Brilliant, your wallet is ready!" label
    And I should see a "Buy or deposit to get started." label
