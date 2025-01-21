import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.pageobject.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseUITest{
    private WebDriver driver;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserApi userApi;
    private UserDataLombok user;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();

        userApi = new UserApi();
        user = UserGenerator.getRandomUser();

        // Регистрация пользователя через API
        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(), user.getPassword(), user.getName());
        ValidatableResponse response = userApi.registerUser(requestBody);
        response.statusCode(200);

        open(CONSTRUCTOR_PAGE_URL);

        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("Тест проверяет вход пользователя через кнопку 'Войти в аккаунт' на главной странице.")
    public void loginFromMainPageTest() {
        constructorPage.goToAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        profilePage.waitForProfileButton(); // Ожидание кнопки
        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
        profilePage.logout();

    }


    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» со страницы конструктора")
    @Description("Тест проверяет вход через кнопку 'Личный кабинет' со страницы конструктора.")
    public void loginFromProfileButtonTest() {
        constructorPage.goToProfile();  // Переход к форме входа
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        profilePage.waitForProfileButton(); // Ожидание кнопки
        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
        profilePage.logout();
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме регистрации")
    @Description("Тест проверяет вход через кнопку 'Войти' в форме регистрации.")
    public void loginFromRegistrationPageTest() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.goToRegister();  // Переход к форме регистрации
        registerPage.goToLogin(); // Переход на страницу логина
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        profilePage.waitForProfileButton(); // Ожидание кнопки
        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
        profilePage.logout();
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
    @Description("Тест проверяет вход через кнопку 'Войти' в форме восстановления пароля.")
    public void loginFromForgotPasswordPageTest() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.goToForgotPassword(); // Переход на страницу восстановления пароля
        forgotPasswordPage.loginButtonClick(); // Клик по кнопке "Войти"

        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        profilePage.waitForProfileButton(); // Ожидание кнопки
        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
        profilePage.logout();
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(user.getEmail(), user.getPassword());
        userApi.deleteUser(deleteToken);
        if (driver != null) {
            driver.quit();
            closeWebDriver(); // Закрытие драйвера
        }
    }
}
