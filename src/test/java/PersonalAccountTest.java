import api.UserApi;
import api.UserDataLombok;
import api.UserGenerator;
import api.UserLogin;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseUITest{
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
        user = UserGenerator.getRandomUser(); // Генерируем данные для нового пользователя
        userApi.registerUser(user);

        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет через кнопку «Личный кабинет»")
    @Description("Тест проверяет возможность перехода в личный кабинет после входа.")
    public void goToPersonalAccountTest() {
            constructorPage.goToProfile();  // Переход к форме входа
            loginPage.login(user.getEmail(), user.getPassword());
            constructorPage.goToProfile();

        profilePage.waitForProfileButton(); // Ожидание кнопки

        boolean isProfileButtonVisible = profilePage.getProfileButton().isDisplayed();
        assertTrue("Кнопка «Профиль» не видна.", isProfileButtonVisible);
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(new UserLogin(user.getEmail(), user.getPassword())); // Получаем токен для удаления пользователя
        userApi.deleteUser(deleteToken); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}
