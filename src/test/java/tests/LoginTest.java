package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {user, password, "positive", null},
                {user, " ", "emptyPassword", "Please enter a password."},
                {" ", password, "emptyEmail", "Please enter your e-mail address."},
                {user, "Q12345", "wrongPassword", "Invalid login credentials. Please try again."}
        };
    }

    @Test(dataProvider = "loginData")
    public void checkLogin(String user, String password, String testCase, String expectedErrorMessage) {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password);
        if (testCase.equals("positive")) {
            loginPage.assertNoErrorMessageVisible();
            assertEquals(userProfilePage.getSettingsText(), "Settings",
                    "User page did not open!");
        } else if (testCase.equals("wrongPassword")) {
            assertEquals(loginPage.getErrorText(), expectedErrorMessage,
                    "No error message appeared!");
        } else
            assertEquals(loginPage.getErrorEmptyText(), expectedErrorMessage,
                    "No error message appeared!");
    }
}
