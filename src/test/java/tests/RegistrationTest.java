package tests;

import dto.Account;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.UserProfilePage;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTest {
    Account account = Account.builder()
            .firstName("Taha")
            .lastName("Yuldasheva")
            .emailAddress("test" + System.currentTimeMillis() + "@example.com")
            .timeZone("(GMT+01:00) Sarajevo, Skopje, Warsaw, Zagreb")
            .password("Q123456q")
            .reTypePassword("Q123456q")
            .build();


    @Description("Checking registration with valid data on the 'Registration' page")
    @Test(testName = "Positive test of the registration page",
            description = "Checking registration with valid data on the 'Registration' page")
    public void checkCreateAccount() {
        registrationPage.openPage()
                .isPageOpened()
                .createAccount(account)
                .clickCreateNewAccountButton();
        registrationPage.assertNoErrorMessageVisible();

        assertEquals($(UserProfilePage.USER_PROFILE_LOCATOR).getText(), "Settings",
                "Страница пользователя не открылась!");
    }

    @Description("Checking registration that the message" +
            "'This field is required.' appears when the 'Last Name' field is empty")
    @Test(testName = "Negative test of the registration page",
            description = "Checking registration that the message " +
                    "'This field is required.' appears when the 'Last Name' field is empty")
    public void checkLoginWithEmptyPassword() {
        registrationPage.openPage()
                .isPageOpened()
                .createAccountWithoutLastName(account)
                .clickCreateNewAccountButton();
        assertEquals($(RegistrationPage.ERROR_MESSAGE_EMPTY).getText(), "This field is required.",
                "No error message appeared!");
    }
}

