# Appium Mobile Automation POC

This project is a mobile automation framework using **Appium**, **Java**, and **TestNG** following the **Page Object Model (POM)**. It supports both **Android** and **iOS** platforms on local emulators/simulators and **BrowserStack**.

## Prerequisites
- **Java 17**
- **Maven**
- **Appium Server** (v2.5.3 recommended)
  - `npm install -g appium@2.5.3`
- **Android SDK** & **Xcode** (for local runs)

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
Update [src/test/resources/config.properties](./src/test/resources/config.properties) to switch between local and BrowserStack runs:

- **Local Run**: Set `run.mode=local`
- **BrowserStack**: Set `run.mode=browserstack` and provide your `browserstack.user` and `browserstack.key`.

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
Run specific platform suites or both using Maven profiles:
- **Android Suite**: `mvn test -Pandroid`
- **iOS Suite**: `mvn test -Pios`
- **Both Android and iOS**: `mvn test -Pall-tests`

## Running on BrowserStack
1. Set `run.mode=browserstack` in `config.properties`.
2. Upload your `.apk` and `.ipa` files to BrowserStack and update `browserstack.app_url_android` and `browserstack.app_url_ios`.
3. Execute the tests using the same Maven commands as above.

## Troubleshooting
- **NoSuchElementException**: If an element is not found, check if the locator needs adjustment for the current app version. Android locators should ideally use `accessibilityId`.
- **SessionNotCreatedException (Android)**: Ensure `ANDROID_HOME` is set.
- **SessionNotCreatedException (iOS)**: Ensure the `.ipa` or `.app.zip` is a valid archive and not corrupted.
- **EACCES (NPM)**: Run `sudo chown -R $(whoami) ~/.npm` to fix local npm permissions.
