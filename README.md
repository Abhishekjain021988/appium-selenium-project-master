# Appium QA Framework

This repo is a starter `Appium` QA framework to automate tests for both `Android` and `iOS`  Bank application. 
This guide will help you setup and run the testing environment.

# Getting Started

## Pre-Requisites 
The following tools are required to be downloaded and installed:

-   [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) (Enterprise Edition if license is available)
-   [Android Studio](https://developer.android.com/studio) + Android Emulator with API Level 26 or higher.
-   [Xcode](https://developer.apple.com/xcode/) or download it directly from the **App Store**
-   [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
-   [Appium Desktop](http://appium.io/downloads.html)

## Setup

### Clone this repo:

```bash
git clone 
```

Then open the cloned repo with `IntelliJ`, the project will build and download all the `Maven` dependencies.

**To improve the performance of test execution on simulator:**

-   Run android simulator `Nexus_5X_API_26` from the AVD manager in android studio
-   Disable `Window animation scale`, `Transition animation scale`, and `Animator duration scale` in android
-   Follow the below steps to disable animation - 
    -   Make sure developer options in simulator are enabled. If they're not, go to Settings > About phone, then tap on Build number several times to enable it
    -   Go to Settings > Developer options, and scroll down to Window animation scale, Transition animation scale, and Animator duration scale
    -   Tap on each of the animation options and turn them off
 
### Run Tests

**To run the tests from the IDE:**

-   Download the newest apk and app.zip files from jenkins and copy to a folder in your laptop
-   Update application.properties with the path to the app files (note, these changes shouldn't be committed)
-   Start `Appium` Server.
-   For `Android`, launch an emulator with API Level 26 or higher. For `iOS`, the simulator will launch automatically once you run the tests.
-   Open `com..channels.qa.RunnerTests.java` and press ^R or right click and press `Run 'Test'`   
**To run the tests from Maven command line:**

-   From the terminal, navigate to the project root directory
-   Run the following:

```bash
mvn test -Dplatform.name=<platform_name> -Dapp.path=<app_path> 
```
-   `platform.name` should be either `Android` or `iOS`
-   `app.path` should be e.g. `/Users/some path/app/app-uat.apk`

### SQLite Database

**To create an SQLite database locally follow these steps:**
-   Navigate to the project root directory
-   Open a terminal window and type:
```bash
sqlite3 database.sqlite
```
-   Inside SQLite cli tool, now we create the table called `TestData` with the following command:
```bash
create table TestData(id varchar, date Date, data varchar);
```

The database file will then appear in the project root directory as `database.sqlite`

# Major Libraries / Tools

| Category                           | Library/Tool      | Link                                                          |
|---------------------------------   |----------------   |------------------------------------------------------------   |
| QA Automation Framework            | Appium Java       | https://github.com/appium/java-client                         |
| Boilerplate Code Generation        | Lombok (see below) | https://projectlombok.org/                                   |
| Automate Build & Release           | Maven             | https://docs.fastlane.tools/actions/xcodebuild/               |
| Base Testing Framework             | TestNG            | http://testng.org/                                            |
| BDD Testing Framework              | Cucumber          | https://cucumber.io/                                          |
| Static Code Style Check (Lint)     | Checkstyle        | http://checkstyle.sourceforge.net/                            |
| Static Code Analysis               | PMD and SpotBugs  | https://pmd.github.io/ and https://spotbugs.github.io/        |
| Continous Integration              | Jenkins           | https://jenkins.io/                                           |
| Static Code Analysis Integration   | SonarQube         | https://www.sonarqube.org/                                    |

## Below is a list of Maven plugins used:

* **maven-checkstyle-plugin**: run Checkstyle using the [./checkstyle.xml](./checkstyle.xml) in the root of the project. This is based on the [Google Java Style](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)
* **spotbugs-maven-plugin**: run Spotbugs static analysis
* **maven-pmd-plugin**: run PMD static analysis
* **maven-surefire-plugin**: run the unit pomtests