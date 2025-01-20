import api.UserApi;
import org.example.pageobject.ConstructorPage;
import org.example.pageobject.LoginPage;
import org.example.pageobject.ProfilePage;
import org.example.pageobject.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationParamTest {

    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String invalidPassword;
    private UserApi userApi;
    private ProfilePage profilePage;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        constructorPage.goToProfile();

        loginPage = new LoginPage();
        loginPage.goToRegister();

        registrationPage = new RegistrationPage();

        userApi = new UserApi();
        profilePage = new ProfilePage();
    }

    public RegistrationParamTest(String invalidPassword) {
        this.invalidPassword = invalidPassword;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"12345"},
                {"1234"},
                {"12"},
                {"1"}
        });
    }

    @Test
    public void errorTextForInvalidPassword() {
        registrationPage.goRegister("Тест56", "test@example.com", invalidPassword, true);

        registrationPage.waitForIncorrectPasswordText();
        registrationPage.waitForRedBorder();

        assertTrue("Сообщение об ошибке не видно для пароля: " + invalidPassword,
                registrationPage.getIncorrectPasswordText().isDisplayed());
        assertTrue("Красная рамка не видна для пароля: " + invalidPassword,
                registrationPage.getRedBorder().isDisplayed());
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(email, password);
        userApi.deleteUser(deleteToken, password); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}
