import api.UserApi;
import io.qameta.allure.Step;
import org.example.pageobject.ConstructorPage;
import org.example.pageobject.LoginPage;
import org.example.pageobject.ProfilePage;
import org.example.pageobject.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;


public class RegistrationTest {

    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private UserApi userApi;
    private ProfilePage profilePage;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_URL);

        constructorPage = new ConstructorPage();
        constructorPage.goToProfile();

        loginPage = new LoginPage();
        loginPage.goToRegister();

        registrationPage = new RegistrationPage();

        userApi = new UserApi();
        profilePage = new ProfilePage();

        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("browser.properties")) {
            if (input == null) {
                throw new IOException("Не удалось найти файл browser.properties");
            }

            properties.load(input);
            name = properties.getProperty("name");
            email = properties.getProperty("email");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Step("Проверка успешной регистрации")
    public void successfulRegistration() {
        registrationPage.goRegister(name, email, password, true);

        loginPage.login(email, password);
        loginPage.loginButtonClick();

        constructorPage.waitForCreateOrderButton();

        assertTrue("Кнопка «Оформить заказ» не видна.", constructorPage.getCreateOrderButton().isDisplayed());
    }


    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(email, password);
        userApi.deleteUser(deleteToken, password); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}



