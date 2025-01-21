import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pageobject.ConstructorPage;
import org.example.pageobject.LoginPage;
import org.example.pageobject.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseUITest {
    private WebDriver driver;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private UserApi userApi;
    private UserDataLombok user;

    @Before
    public void setUp() {
        userApi = new UserApi();
        user = UserGenerator.getRandomUser();

        // Регистрация пользователя через API
        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(), user.getPassword(), user.getName());
        ValidatableResponse response = userApi.registerUser(requestBody);
        response.statusCode(200);// Проверка успешной регистрации

        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }

    @Test
    @DisplayName("Тест выхода из аккаунта")
    @Description("Проверка правильности работы кнопки выхода из аккаунта.")
    public void logoutTest() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile(); // Переход на страницу профиля
        profilePage.logout(); // Нажимаем кнопку "Выход"

        loginPage.waitForLoginButton(); // ожидание кнопки
        assertTrue("Кнопка «Войти» не видна.", loginPage.getLoginButton().isDisplayed());
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(user.getEmail(), user.getPassword());
        userApi.deleteUser(deleteToken); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}

