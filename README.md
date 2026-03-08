# Appium Mobile Automation POC

This project is a mobile automation framework using **Appium**, **Java**, and **TestNG** following the **Page Object Model (POM)**. It supports both **Android** and **iOS** platforms on local emulators/simulators and **BrowserStack**.

## Prerequisites
- **Java 17**
- **Maven**
- **Appium Server** (v2.5.3 recommended)
  - `npm install -g appium@2.5.3`
- **Android SDK** & **Xcode** (for local runs)
- **BrowserStack Account** (optional, for cloud testing)

## Driver Installation
Before running tests locally, you must install the required Appium drivers:

### 1. Android Driver (UiAutomator2)
```bash
appium driver install uiautomator2@2.34.0
```

### 2. iOS Driver (XCUITest)
```bash
appium driver install xcuitest@5.12.0
```

## Configuration
Update [src/test/resources/config.properties](./src/test/resources/config.properties):

```properties
# Run mode: local or browserstack
run.mode=local

# BrowserStack Configuration
browserstack.user=your_username
browserstack.key=your_access_key
browserstack.server=hub-cloud.browserstack.com
browserstack.app_url_android=bs://app_hash_from_browserstack
browserstack.app_url_ios=bs://app_hash_from_browserstack

# Android Configuration
android.device.name=Pixel_6_Pro_API_36
android.os.version=16

# iOS Configuration
ios.device.name=iPhone SE (3rd generation)
ios.os.version=17.4

# Local Configuration
local.appium_server=http://127.0.0.1:4723
local.app_path_android=src/test/resources/sample.apk
local.app_path_ios=src/test/resources/sample.app
```

## Running Tests Locally

### 1. Start Appium Server
```bash
appium --address 127.0.0.1 --port 4723 --use-drivers uiautomator2,xcuitest
```

### 2. Start Android Emulator
- Open Android Studio -> Device Manager -> Start your emulator.
- Ensure `ANDROID_HOME` is exported in your environment.

### 3. Start iOS Simulator (macOS only)
- Open Xcode -> Open Developer Tool -> Simulator.
- Ensure you have a simulator named matching `ios.device.name` in `config.properties`.

### 4. Execute Tests
To ensure old test results are cleared, always use `clean` before `test`:

**Local Android Tests:**
```bash
mvn clean test -Pandroid
```

**Local iOS Tests:**
```bash
mvn clean test -Pios
```

**Both Local Android and iOS:**
```bash
mvn clean test -Pall-tests
```

## Allure Reporting

### Generate Report Locally
To generate and view the Allure report after running tests:
```bash
# Generate and open in a web browser
mvn allure:serve

# Or generate static HTML in target/site/allure-maven-plugin
mvn allure:report
```
Note: Results are stored in `target/allure-results` and are automatically cleared by `mvn clean`.

## Running on Jenkins

### 1. Prerequisites
- **Jenkins Plugins**:
  - `HTML Publisher Plugin` (to view Allure reports)
  - `Credentials Plugin`
- **Global Tool Configuration**:
  - Add a **Maven** installation named `maven`
  - Add a **JDK** installation named `java-17` (if not in system PATH)

### 2. Setup Credentials
Go to **Manage Jenkins > Credentials** and add:
- **ID**: `browserstack-user` (Secret text)
- **ID**: `browserstack-key` (Secret text)

### 3. Execution
The project includes a `Jenkinsfile` that handles the build, BrowserStack execution, and Allure report generation.

## Running on BrowserStack

### Setup
1. Create a BrowserStack account at [https://www.browserstack.com](https://www.browserstack.com)
2. Upload your app to BrowserStack:
   - Android: Upload `.apk` file and note the `app_id`
   - iOS: Upload `.ipa` file and note the `app_id`
3. Update `config.properties` with:
   - `run.mode=browserstack`
   - `browserstack.user=your_username`
   - `browserstack.key=your_access_key`
   - `browserstack.app_id=bs://your_app_hash`

### Execute Tests on BrowserStack

**BrowserStack Android Tests:**
```bash
mvn test -PBandroid
```

**BrowserStack iOS Tests:**
```bash
mvn test -PBios
```

## Troubleshooting
- **NoSuchElementException**: If an element is not found, check if the locator needs adjustment for the current app version. Android locators should ideally use `accessibilityId`.
- **SessionNotCreatedException (Android)**: Ensure `ANDROID_HOME` is set.
- **SessionNotCreatedException (iOS)**: Ensure the `.ipa` or `.app.zip` is a valid archive and not corrupted.
- **EACCES (NPM)**: Run `sudo chown -R $(whoami) ~/.npm` to fix local npm permissions.
- **BrowserStack Connection Error**: Verify `browserstack.user` and `browserstack.key` in `config.properties`.
