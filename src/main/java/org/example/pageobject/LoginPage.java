package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private WebDriver driver;


    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private final String stringEmailInputLocator = "//input[@type='text']"; // строка ввода Email
    private final String stringPasswordInputLocator = "//input[@type='password']"; // строка ввода пароля
    private final String loginButtonLocator = "//button[contains(@class, 'button_button__33qZ0')]"; // кнопка Войти
    private final String registerButtonLocator = "//a[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']"; // кнопка зарегистрироваться
    private final String forgotPasswordButtonLocator = "//a[contains(@class, 'Auth_link__1fOlj') and @href='/forgot-password']"; // кнопка восстановления пароля

    private SelenideElement emailInput = $x(stringEmailInputLocator);
    private SelenideElement passwordInput = $x(stringPasswordInputLocator);
    private SelenideElement loginButton = $x(loginButtonLocator);
    private SelenideElement registerButton = $x(registerButtonLocator);
    private SelenideElement forgotPasswordButton = $x(forgotPasswordButtonLocator);

    public LoginPage(WebDriver driver) {
        this.driver= driver;
    }
    public LoginPage(){
    }

    @Step("Ожидание появления кнопки 'Войти'")
    public void waitForLoginButton() {
        loginButton.shouldBe(visible);
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    @Step("Вход с Email: {email} и Пароль: {password}")
    public void login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
}

    @Step("Переход на страницу регистрации")
    public void goToRegister() {

        registerButton.click();
    }

    @Step("Переход на страницу восстановления пароля")
    public void goToForgotPassword() {

        forgotPasswordButton.click();
    }
}

