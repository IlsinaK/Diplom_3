package org.example.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private WebDriver driver;

    // Элементы для страницы регистрации
    private String stringNameInputLocator = ".//input[@name='name' and preceding-sibling::label[text()='Имя']]"; // строка ввода Имя
    private String stringEmailInputLocator = ".//input[@name='name' and preceding-sibling::label[text()='Email']]"; // строка ввода Email
    private String stringPasswordInputLocator = ".//input[@type='password']"; // строка ввода пароль
    public String registerButtonLocator = ".//button[contains(@class, 'button_button__33qZ0')]"; // кнопка зарегистрироваться
    private String loginButtonLocator = ".//a[contains(@class, 'Auth_link')]"; // кнопка Войти
    private SelenideElement incorrectPasswordText = $x(".//p[contains(@class, 'input__error')]"); // Некорректный пароль текст
    private SelenideElement redBorder = $x(".//div[contains(@class, 'input_status_error')]"); // выделение поля пароль красным
    private SelenideElement passwordField = $x(".//input[@type='password']");

    public RegistrationPage(WebDriver driver) {
        this.driver= driver;
    }
    public RegistrationPage(){

    }

    public void clearPasswordField() {
        passwordField.clear();
    }

    // Метод для регистрации
    public void goRegister(String name, String email, String password, boolean shouldClick) {
        InputElement nameInput = new InputElement(stringNameInputLocator);
        nameInput.clearAndSetValue(name);

        InputElement emailInput = new InputElement(stringEmailInputLocator);
        emailInput.clearAndSetValue(email);

        InputElement passwordInput = new InputElement(stringPasswordInputLocator);
        passwordInput.clearAndSetValue(password);

        removeFocusFromPasswordField();

        if (shouldClick) {
            ButtonElement loginButton = new ButtonElement(loginButtonLocator);
            loginButton.clickButton(); // Нажимаем кнопку только если shouldClick=true
        }
    }
    public void removeFocusFromPasswordField() {
        // Убираем фокус с поля пароля, установив его на тело страницы
        Selenide.executeJavaScript("document.body.focus();");
    }


    public void waitForIncorrectPasswordText() {
        incorrectPasswordText.shouldBe(visible);
    }

    public SelenideElement getIncorrectPasswordText() {
        return incorrectPasswordText;
    }
        public void waitForRedBorder() {
            redBorder.shouldBe(visible);
        }

        public SelenideElement getRedBorder() {
            return redBorder;
        }


    public void passwordInput(String password) {
        clearPasswordField(); // Сначала очищаем поле
        if (password != null) {
            driver.findElement(By.id(stringPasswordInputLocator)).sendKeys(password); // Вводим пароль
        }
    }


    // Переход на страницу входа
    public void goToLogin() {

        ButtonElement goToLogin = new ButtonElement(loginButtonLocator);
        goToLogin.clickButton();
    }
    public void goRegister() {

        ButtonElement goRegister = new ButtonElement(registerButtonLocator);
        goRegister.clickButton();
    }


}
