import api.UserApi;
import api.UserDataLombok;
import api.UserLogin;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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


public class RegistrationTest extends BaseUITest {

    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private UserApi userApi;
    private ProfilePage profilePage;
    private String name;
    private String email;
    private String password;
    private UserDataLombok user;

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
        user = new UserDataLombok(email, password, name);

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
    @DisplayName("Проверка успешной регистрации")
    @Description("Тест проверяет успешную регистрацию пользователя и отображение кнопки оформления заказа.")
    public void successfulRegistrationTest() {
        registrationPage.goRegister(name, email, password);

        loginPage.login(email, password);

        constructorPage.waitForCreateOrderButton();

        assertTrue("Кнопка «Оформить заказ» не видна.", constructorPage.isCreateOrderButtonVisible());
    }


    @After
    public void tearDown() {
        if (userApi != null) {
            // Создание объекта UserLogin с email и password
            UserLogin userLogin = new UserLogin(email, password);

            // Получение токена для удаления пользователя
            String deleteToken = userApi.getToken(userLogin);

            if (deleteToken != null) { // Проверка на null
                userApi.deleteUser(deleteToken); // Удаление пользователя через API
            } else {
                System.err.println("Не удалось получить токен для удаления пользователя.");
            }
        }
        closeWebDriver(); // Закрытие драйвера
    }

}






