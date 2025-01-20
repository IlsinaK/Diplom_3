package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";


    public String constructorButtonLocator = ".//a[contains(@class, 'header__link__3D_hX') and .//p[text()='Конструктор']]";  // кнопка Конструктор
    public String logoStellarBurgersButtonLocator = ".//div[contains(@class, 'AppHeader_header__logo__2D0X2')]"; // кнопка логотипа Stellar Burgers
    public String exitButtonLocator = ".//button[contains(@class, 'Account_button__14Yp3')]"; // кнопка выход
    public SelenideElement profileButton = $x("//li[contains(@class,'Account_listItem__35dAP')]//a[text()='Профиль']"); // кнопка Профиль
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public ProfilePage(){

    }

    public void waitForProfileButton() {
        profileButton.shouldBe(visible);
    }

    public SelenideElement getProfileButton() {
        return profileButton;
    }


    public void logout() {     //выход из аккаунта

        ButtonElement logout = new ButtonElement(exitButtonLocator);
        logout.clickButton();
    }
    public void goToConstructor() {  //переход через нажатие на Конструктор

        ButtonElement goToConstructor = new ButtonElement(constructorButtonLocator);
        goToConstructor.cscrollAndCickButton();
    }

    public void clickLogo() {  //переход через нажатие на лого

        ButtonElement clickLogo = new ButtonElement(logoStellarBurgersButtonLocator);
        clickLogo.cscrollAndCickButton();

    }
}
