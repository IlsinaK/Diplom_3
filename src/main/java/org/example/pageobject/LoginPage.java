package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private WebDriver driver;


    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private String stringEmailInputLocator = "//input[@type='text']"; // строка ввода Email
    private String stringPasswordInputLocator = "//input[@type='password']"; // строка ввода пароля
    private static String loginButtonLocator = "//button[contains(@class, 'button_button__33qZ0')]"; // кнопка Войти
    private String registerButtonLocator = "//a[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']"; // кнопка зарегистрироваться
    private String forgotPasswordButtonLocator = "//a[contains(@class, 'Auth_link__1fOlj') and @href='/forgot-password']"; // кнопка восстановления пароля
    private SelenideElement loginButton = $x("//button[contains(@class, 'button_button__33qZ0')]");

    public LoginPage(WebDriver driver) {
        this.driver= driver;
    }
    public LoginPage(){

    }

    public void waitForLoginButton() {
        loginButton.shouldBe(visible);
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

public void login(String email, String password) {
    InputElement emailInput = new InputElement(stringEmailInputLocator);
    emailInput.clickAndSetValue(email);

    InputElement passwordInput = new InputElement(stringPasswordInputLocator);
    passwordInput.clickAndSetValue(password);

    ButtonElement loginButton = new ButtonElement(LoginPage.loginButtonLocator);
        loginButton.cscrollAndCickButton();
}

    public void goToRegister() {
        ButtonElement goToRegister = new ButtonElement(registerButtonLocator);
        goToRegister.clickButton();
    }

    public void goToForgotPassword() {
        ButtonElement goToForgotPassword = new ButtonElement(forgotPasswordButtonLocator);
        goToForgotPassword.clickButton();
    }

    public void loginButtonClick() {
        ButtonElement loginButtonClick = new ButtonElement(loginButtonLocator);
        loginButtonClick.clickButton();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl(); // Получение текущего URL
    }


}

