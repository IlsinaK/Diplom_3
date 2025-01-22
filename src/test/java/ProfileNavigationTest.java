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
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;


public class ProfileNavigationTest extends BaseUITest{
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
        user = UserGenerator.getRandomUser(); // Генерируем данные для нового пользователя
        userApi.registerUser(user);

        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
        loginPage = new LoginPage();
        registerPage = new RegistrationPage();
        forgotPasswordPage = new ForgotPasswordPage();
        profilePage = new ProfilePage();


        constructorPage.goToAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        constructorPage.goToProfile();

    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор через кнопку «Конструктор»")
    @Description("Тест проверяет возможность перехода из личного кабинета в конструктор через соответствующую кнопку.")
    public void testGoToConstructorFromProfile() {
        profilePage.goToConstructor(); // Переход в конструктор
        constructorPage.waitForAssembleBurger(); // Ожидание видимости текста «Соберите бургер»
        assertTrue("Текст «Соберите бургер» не виден.", constructorPage.getAssembleBurger().isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор через логотип Stellar Burgers")
    @Description("Тест проверяет возможность перехода из личного кабинета в конструктор через клик на логотип.")
    public void goToConstructorFromLogoTest() {
        profilePage.clickLogo(); // Клик на логотип
        constructorPage.waitForAssembleBurger(); // Ожидание видимости текста «Соберите бургер»

        assertTrue("Текст «Соберите бургер» не виден.", constructorPage.getAssembleBurger().isDisplayed());
    }

    @After
    public void tearDown() {
        String deleteToken = userApi.getToken(new UserLogin(user.getEmail(), user.getPassword())); // Получаем токен для удаления пользователя
        userApi.deleteUser(deleteToken); // Удаление пользователя через API
        closeWebDriver(); // Закрытие драйвера
    }
}
