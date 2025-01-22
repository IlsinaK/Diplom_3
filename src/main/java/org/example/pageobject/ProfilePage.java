package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";


    private final String constructorButtonLocator = ".//a[contains(@class, 'header__link__3D_hX') and .//p[text()='Конструктор']]";  // кнопка Конструктор
    private final String logoStellarBurgersButtonLocator = ".//div[contains(@class, 'AppHeader_header__logo__2D0X2')]"; // кнопка логотипа Stellar Burgers
    private final String exitButtonLocator = ".//button[contains(@class, 'Account_button__14Yp3')]"; // кнопка выход
    private final SelenideElement profileButton = $x("//li[contains(@class,'Account_listItem__35dAP')]//a[text()='Профиль']"); // кнопка Профиль


    private WebDriver driver;

    public ProfilePage(WebDriver driver) {

        this.driver = driver;
    }
    public ProfilePage(){

    }

    @Step("Ожидание появления кнопки 'Профиль'")
    public void waitForProfileButton() {

        profileButton.shouldBe(visible);
    }

    @Step("Выход из аккаунта")
    public void logout() {

        new ButtonElement(exitButtonLocator).clickButton();
    }

    @Step("Переход на страницу 'Конструктор'")
    public void goToConstructor() {

        new ButtonElement(constructorButtonLocator).clickButton();
    }

    @Step("Переход на страницу 'Конструктор' через нажатие на логотип Stellar Burgers")
    public void clickLogo() {

        new ButtonElement(logoStellarBurgersButtonLocator).clickButton();

    }
    public SelenideElement getProfileButton() {

        return profileButton;
    }
}
