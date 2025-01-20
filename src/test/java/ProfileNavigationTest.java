import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import io.qameta.allure.Step;
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


public class ProfileNavigationTest {
    private WebDriver driver;
    private ProfilePage profilePage;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserApi userApi;
    private UserDataLombok user;

    @Before
    public void setUp() {
        userApi = new UserApi();
        user = UserGenerator.getRandomUser();

        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();

        // Регистрация пользователя через API
        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(), user.getPassword(), user.getName());
        ValidatableResponse response = userApi.registerUser(requestBody);
        response.statusCode(200);

        constructorPage.goToAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

    }

    @Test
    @Step("Проверка перехода из личного кабинета в конструктор через кнопку «Конструктор»")
    public void testGoToConstructorFromProfile() {
        profilePage.goToConstructor(); // Переход в конструктор

        if (!constructorPage.getAssembleBurger().isDisplayed()) {  // Проверка видимости Текста «Соберите бургер»
            System.out.println("Текст «Соберите бургер» не виден.");
        } else {
            System.out.println("Текст «Соберите бургер» виден.");
        }

        constructorPage.waitForAssembleBurger(); // ожидание кнопки

        assertTrue("Текст «Соберите бургер» не виден.", constructorPage.getAssembleBurger().isDisplayed());
    }

    @Test
    @Step("Проверка перехода из личного кабинета в конструктор через логотип Stellar Burgers")
    public void testGoToConstructorFromLogo() {
        profilePage.clickLogo(); // Клик на логотип

        if (!constructorPage.getAssembleBurger().isDisplayed()) {  // Проверка видимости Текста «Соберите бургер»
            System.out.println("Текст «Соберите бургер» не виден.");
        } else {
            System.out.println("Текст «Соберите бургер» виден.");
        }

        constructorPage.waitForAssembleBurger(); // ожидание кнопки

        assertTrue("Текст «Соберите бургер» не виден.", constructorPage.getAssembleBurger().isDisplayed());
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(user.getEmail(), user.getPassword());
        userApi.deleteUser(deleteToken, user.getPassword()); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}
