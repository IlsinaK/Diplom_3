package org.example.pageobject;

import io.qameta.allure.Step;
import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final String loginButtonLocator = ".//a[@class='Auth_link__1fOlj' and @href='/login']"; // Кнопка "Войти" внизу страницы



    public ForgotPasswordPage(WebDriver driver) {

        this.driver= driver;
    }
    public ForgotPasswordPage(){

    }

    @Step("Нажать на кнопку 'Войти'")
    public void loginButtonClick() {
      new ButtonElement(loginButtonLocator).clickButton();
    }
}
