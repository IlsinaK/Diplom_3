import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.ConstructorPage;
import org.example.pageobject.LoginPage;
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
public class RegistrationParamTest extends BaseUITest{

    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String invalidPassword;
    private UserApi userApi;

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

    @Before
    public void setUp() {
        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        constructorPage.goToProfile();

        loginPage = new LoginPage();
        loginPage.goToRegister();

        registrationPage = new RegistrationPage();
        userApi = new UserApi();
    }


    @Test
    @DisplayName("Проверка текста ошибки для недопустимого пароля при регистрации")
    @Description("Тест проверяет, что отображается сообщение об ошибке и красная рамка для недопустимого пароля.")

    public void errorTextForInvalidPasswordTest() {
        registrationPage.goRegister("Тест56", "test@example.com", invalidPassword);

        registrationPage.waitForIncorrectPasswordText();
        registrationPage.waitForRedBorder();

        assertTrue("Сообщение об ошибке не видно для пароля: " + invalidPassword,
                registrationPage.isIncorrectPasswordTextVisible());
        assertTrue("Красная рамка не видна для пароля: " + invalidPassword,
                registrationPage.isRedBorderVisible());
    }

    @After
    public void tearDown() {

        closeWebDriver(); // Закрытие драйвера
    }
}
