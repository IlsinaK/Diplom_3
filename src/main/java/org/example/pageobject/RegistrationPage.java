package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private WebDriver driver;


    private final String nameInputLocator = ".//input[@name='name' and preceding-sibling::label[text()='Имя']]"; // строка ввода Имя
    private final String emailInputLocator = ".//input[@name='name' and preceding-sibling::label[text()='Email']]"; // строка ввода Email
    private final String passwordInputLocator = ".//input[@type='password']"; // строка ввода пароль
    private final String registerButtonLocator = ".//button[contains(@class, 'button_button__33qZ0')]"; // кнопка зарегистрироваться
    private final String loginButtonLocator = ".//a[contains(@class, 'Auth_link')]"; // кнопка Войти

    private final SelenideElement incorrectPasswordText = $x(".//p[contains(@class, 'input__error')]"); // Некорректный пароль текст
    private final SelenideElement redBorder = $x(".//div[contains(@class, 'input_status_error')]"); // выделение поля пароль красным
    private final SelenideElement passwordField = $x(".//input[@type='password']");

    private final InputElement nameInput = new InputElement(nameInputLocator);
    private final InputElement emailInput = new InputElement(emailInputLocator);
    private final InputElement passwordInput = new InputElement(passwordInputLocator);
    private final ButtonElement registerButton = new ButtonElement(registerButtonLocator);
    private final ButtonElement loginButton = new ButtonElement(loginButtonLocator);

    public RegistrationPage(WebDriver driver) {

        this.driver= driver;
    }
    public RegistrationPage(){

    }

    @Step("Регистрация пользователя")
     public void goRegister (String name, String email, String password) {
            nameInput.clearAndSetValue(name);
            emailInput.clearAndSetValue(email);
            passwordInput.clearAndSetValue(password);
            registerButton.clickButton();
        }

    @Step("Ожидание текста некорректного пароля")
    public void waitForIncorrectPasswordText() {
        incorrectPasswordText.shouldBe(visible);
    }

    @Step("Проверка видимости текста некорректного пароля")
    public boolean isIncorrectPasswordTextVisible() {
        return incorrectPasswordText.isDisplayed();
    }

    @Step("Ожидание красной рамки вокруг поля пароля")
    public void waitForRedBorder() {
            redBorder.shouldBe(visible);
        }
    @Step("Проверка видимости красной рамки")
    public boolean isRedBorderVisible() {

        return redBorder.isDisplayed();
    }

    @Step("Переход на страницу входа")
    public void goToLogin() {
        ButtonElement goToLogin = new ButtonElement(loginButtonLocator);
        goToLogin.clickButton();
    }
}
