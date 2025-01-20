import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.pageobject.ConstructorPage;
import org.example.pageobject.LoginPage;
import org.example.pageobject.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class LogoutTest {
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
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    @Step("Тест выхода из аккаунта")
    public void testLogout() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile(); // Переход на страницу профиля
        profilePage.logout(); // Нажимаем кнопку "Выход"

        System.out.println("Селектор loginButton: " + loginPage.getLoginButton());

        if (!loginPage.getLoginButton().isDisplayed()) {  // Проверка видимости кнопки "Войти"
            System.out.println("Кнопка «Войти» не видна.");
        } else {
            System.out.println("Кнопка «Войти» видна.");
        }

        loginPage.waitForLoginButton(); // ожидание кнопки

        assertTrue("Кнопка «Войти» не видна.", loginPage.getLoginButton().isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие драйвера
        }
    }
}

