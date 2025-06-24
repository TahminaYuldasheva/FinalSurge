package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.*;
import utils.AllureUtils;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseTest {

    LoginPage loginPage;
    RegistrationPage registrationPage;
    AddWorkoutPage addWorkoutPage;
    AddWorkoutModal addWorkoutModal;
    WorkoutDetailsPage workoutDetailsPage;
    TrainingCalendarPage trainingCalendarPage;

    @Parameters({"browser"})
    @BeforeMethod(description = "Opening browser")
    public void setup(@Optional("chrome") String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = "https://log.finalsurge.com/";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.headless = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();
        addWorkoutPage = new AddWorkoutPage();
        addWorkoutModal = new AddWorkoutModal();
        workoutDetailsPage = new WorkoutDetailsPage();
        trainingCalendarPage = new TrainingCalendarPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.reportsFolder = "target/allure-results";
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()){
            WebDriver driver = WebDriverRunner.getWebDriver();
            AllureUtils.takeScreenshot(driver);
        }
        closeWebDriver();
    }
}
