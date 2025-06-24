package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserProfilePage;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Description("Checking login with valid data")
    @Test(testName = "Positive login test", description = "Checking login with valid data")
    public void checkLoginWithPositiveCred() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password)
                .assertNoErrorMessageVisible();
        assertEquals($(UserProfilePage.USER_PROFILE_LOCATOR).getText(), "Settings",
                "User page did not open!");
    }

    @Description("Checking login with empty 'Password' field")
    @Test(testName = "Negative login test with empty password",
            description = "Checking login with empty Password field")
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, " ");
        assertEquals($(LoginPage.ERROR_MESSAGE_EMPTY).getText(), "Please enter a password.",
                "No error message appeared!");
    }

    @Description("Checking login with empty 'Email' field")
    @Test(testName = "Negative login test with empty email",
            description = "Checking login with empty 'Email' field")
    public void checkLoginWithEmptyEmail() {
        loginPage.openPage()
                .isPageOpened()
                .login(" ", password);
        assertEquals($(LoginPage.ERROR_MESSAGE_EMPTY).getText(), "Please enter your e-mail address.",
                "No error message appeared!");
    }

    @Description("Checking login with wrong password")
    @Test(testName = "Negative login test",
            description = "Checking login with wrong password")
    public void checkLoginWithWrongPassword() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, "Q12345");
        assertEquals($(LoginPage.ERROR_MESSAGE).getText(), "Invalid login credentials. Please try again.",
                "No error message appeared!");
    }
}
