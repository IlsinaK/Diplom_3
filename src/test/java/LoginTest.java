import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.pageobject.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class LoginTest {
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

        // Инициализация страниц

        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();
    }

    @Test
    @Step("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginFromMainPage() {
        constructorPage.goToAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        System.out.println("Селектор profileButton: " + profilePage.getProfileButton()); // Логирование селектора

        // Проверка видимости кнопки "Профиль"
        if (!profilePage.getProfileButton().isDisplayed()) {
            System.out.println("Кнопка «Профиль» не видна.");
        } else {
            System.out.println("Кнопка «Профиль» видна.");
        }

        profilePage.waitForProfileButton(); // ожидание кнопки

        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
    }


    @Test
    @Step("Вход через кнопку «Личный кабинет» со страницы конструктора")
    public void loginFromProfileButton() {
        constructorPage.goToProfile();  // Переход к форме входа
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        System.out.println("Селектор profileButton: " + profilePage.getProfileButton());

        // Проверка видимости кнопки "Профиль"
        if (!profilePage.getProfileButton().isDisplayed()) {
            System.out.println("Кнопка «Профиль» не видна.");
        } else {
            System.out.println("Кнопка «Профиль» видна.");
        }

        profilePage.waitForProfileButton(); // ожидание кнопки

        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
    }

    @Test
    @Step("Вход через кнопку «Войти» в форме регистрации")
    public void loginFromRegistrationPage() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.goToRegister();  // Переход к форме регистрации
        registerPage.goToLogin(); // Переход на страницу логина
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        System.out.println("Селектор profileButton: " + profilePage.getProfileButton());

        // Проверка видимости кнопки "Профиль"
        if (!profilePage.getProfileButton().isDisplayed()) {
            System.out.println("Кнопка «Профиль» не видна.");
        } else {
            System.out.println("Кнопка «Профиль» видна.");
        }

        profilePage.waitForProfileButton(); // ожидание кнопки

        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
    }

    @Test
    @Step("Вход через кнопку «Войти» в форме восстановления пароля")
    public void loginFromForgotPasswordPage() {
        constructorPage.goToAccount(); // Переход к форме входа
        loginPage.goToForgotPassword(); // Переход на страницу восстановления пароля
        forgotPasswordPage.loginButtonClick(); // Клик по кнопке "Войти"

        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

        System.out.println("Селектор profileButton: " + profilePage.getProfileButton());

        if (!profilePage.getProfileButton().isDisplayed()) {  // Проверка видимости кнопки "Профиль"
            System.out.println("Кнопка «Профиль» не видна.");
        } else {
            System.out.println("Кнопка «Профиль» видна.");
        }

        profilePage.waitForProfileButton(); // ожидание кнопки

        assertTrue("Кнопка «Профиль» не видна.", profilePage.getProfileButton().isDisplayed());
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(user.getEmail(), user.getPassword());
        userApi.deleteUser(deleteToken, user.getPassword()); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}
