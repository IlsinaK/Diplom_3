package org.example.pageobject;

import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private String loginButtonLocator = ".//a[@class='Auth_link__1fOlj' and @href='/login']"; // Кнопка "Войти" внизу страницы



    public ForgotPasswordPage(WebDriver driver) {
        this.driver= driver;
    }
    public ForgotPasswordPage(){

    }

    public ForgotPasswordPage openForgotPasswordPage(){
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }

    public void loginButtonClick() {
        ButtonElement loginButtonClick = new ButtonElement(loginButtonLocator);
        loginButtonClick.clickButton();
    }
}
