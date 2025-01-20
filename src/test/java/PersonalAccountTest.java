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

public class PersonalAccountTest {
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserApi userApi;
    private UserDataLombok user;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        userApi = new UserApi();
        user = UserGenerator.getRandomUser();

        // Регистрация пользователя через API
        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(), user.getPassword(), user.getName());
        ValidatableResponse response = userApi.registerUser(requestBody);
        response.statusCode(200);

        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();
    }

    @Test
    @Step("Проверка перехода в личный кабинет через кнопку «Личный кабинет»")
    public void testGoToPersonalAccount() {
            constructorPage.goToProfile();  // Переход к форме входа
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
