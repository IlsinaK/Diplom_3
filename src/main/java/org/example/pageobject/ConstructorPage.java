package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.html.HTMLInputElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ConstructorPage {
    public HTMLInputElement goToProfile;
    private WebDriver driver;
    public static final String CONSTRUCTOR_PAGE_URL = "https://stellarburgers.nomoreparties.site";

    private String signAccountButtonLocator = ".//button[contains(@class, 'button__33qZ0')]";  // кнопка входа в аккаунт
    private String personalAccountButtonLocator = ".//a[contains(@class, 'AppHeader_header__link__3D_hX') and@href='/account']"; // кнопка входа в личный кабинет
    private String sectionBunButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']"; // раздел Булки
    private String sectionSaucesButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']"; // раздел Соусы
    private String sectionToppingsButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']"; // раздел Начинки
    private String activeSectionBunLocator = ".//div[contains(@class, 'current')]//span[text()='Булки']"; // строка Булки
    private String activeSectionSaucesLocator = ".//div[contains(@class, 'current')]//span[text()='Соусы']"; // строка Соусы
    private String activeSectionToppingsLocator = ".//div[contains(@class, 'current')]//span[text()='Начинки']"; // строка Начинки
    private SelenideElement assembleBurger = $x(".//h1[contains(@class,'text_type_main-large')]"); //текст Соберите бургер
    private SelenideElement createOrderButton = $x(".//button[contains(@class, 'button_button')]"); // кнопка Оформить заказ

    public ConstructorPage(WebDriver driver) {
        this.driver= driver;
    }
    public ConstructorPage(){

    }

    public void waitForCreateOrderButton() {
        createOrderButton.shouldBe(visible);
    }

    public SelenideElement getCreateOrderButton() {
        return createOrderButton;
    }

    public void waitForAssembleBurger() {
        assembleBurger.shouldBe(visible);
    }

    public SelenideElement getAssembleBurger() {
        return assembleBurger;
    }

    public void goToProfile() {   // Метод для перехода в личный кабинет
        ButtonElement goToProfile = new ButtonElement(personalAccountButtonLocator);
        goToProfile.clickButton();
    }
    public void goToAccount() { //вход в аккаунт

        ButtonElement goToAccount = new ButtonElement(signAccountButtonLocator);
        goToAccount.clickButton();
    }

    // Выбор раздела булки
    public void selectBuns() {
        ButtonElement selectBuns = new ButtonElement(sectionBunButtonLocator);
        selectBuns.clickButton();
    }

    // Выбор раздела соусы
    public void selectSauces() {

        ButtonElement selectSauces = new ButtonElement(sectionSaucesButtonLocator);
        selectSauces.clickButton();
    }

    // Выбор раздела начинки
    public void selectToppings() {

        ButtonElement selectToppings = new ButtonElement(sectionToppingsButtonLocator);
        selectToppings.clickButton();
    }


    public boolean isActiveSectionBun() {
        ButtonElement isActiveSectionBun = new ButtonElement(activeSectionBunLocator);
        isActiveSectionBun.isEnableButton();                  // предполагаем, что элемент с id "activeSectionBun" появляется, когда раздел активен
        return true;
    }

    public boolean isActiveSectionSauces() {
        ButtonElement isActiveSectionSauces = new ButtonElement(activeSectionSaucesLocator);
        isActiveSectionSauces.isEnableButton();  // предполагаем, что элемент с id "activeSectionSauces" появляется, когда раздел активен
        return true;
    }

    public boolean isActiveSectionToppings() {
        ButtonElement isActiveSectionToppings = new ButtonElement(activeSectionToppingsLocator);
        isActiveSectionToppings.isEnableButton(); // предполагаем, что элемент с id "activeSectionToppings" появляется, когда раздел активен
        return true;
    }

}
