package org.example.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.element.ButtonElement;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.html.HTMLInputElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ConstructorPage {
    public HTMLInputElement goToProfile;
    private WebDriver driver;
    public static final String CONSTRUCTOR_PAGE_URL = "https://stellarburgers.nomoreparties.site";

    private final String signAccountButtonLocator = ".//button[contains(@class, 'button__33qZ0')]";  // кнопка входа в аккаунт
    private final String personalAccountButtonLocator = ".//a[contains(@class, 'AppHeader_header__link__3D_hX') and@href='/account']"; // кнопка входа в личный кабинет
    private final String sectionBunButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']"; // раздел Булки
    private final String sectionSaucesButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']"; // раздел Соусы
    private final String sectionToppingsButtonLocator = ".//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']"; // раздел Начинки
    private final String activeSectionBunLocator = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Булки']"; // строка Булки
    private final String activeSectionSaucesLocator = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Соусы']"; // строка Соусы
    private final String activeSectionToppingsLocator = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Начинки']"; // строка Начинки
    private final String assembleBurgerLocator = ".//h1[contains(@class,'text_type_main-large')]"; //текст Соберите бургер
    private final String createOrderButtonLocator = ".//button[contains(@class, 'button_button')]"; // кнопка Оформить заказ

    private SelenideElement createOrderButton = $x(createOrderButtonLocator);
    private SelenideElement assembleBurger = $x(assembleBurgerLocator);

    public ConstructorPage(WebDriver driver) {
        this.driver= driver;
    }
    public ConstructorPage(){

    }

    @Step("Ожидание кнопки 'Оформить заказ'")
    public void waitForCreateOrderButton() {
        createOrderButton.shouldBe(visible);
    }

    @Step("Проверить, видна ли кнопка 'Оформить заказ'")
    public boolean isCreateOrderButtonVisible() {
        return createOrderButton.isDisplayed();
    }

    @Step("Ожидание текста 'Соберите бургер'")
    public void waitForAssembleBurger() {

        assembleBurger.shouldBe(visible);
    }

    public SelenideElement getAssembleBurger() {
        return assembleBurger;
    }

    @Step("Переход в личный кабинет")
    public void goToProfile() {

        new ButtonElement(personalAccountButtonLocator).clickButton();
    }

    @Step("Вход в аккаунт")
    public void goToAccount() {
       new ButtonElement(signAccountButtonLocator).clickButton();
    }

    @Step("Выбор раздела 'Булки'")
    public void selectBuns() {

        new ButtonElement(sectionBunButtonLocator).clickButton();
    }

    @Step("Выбор раздела 'Соусы'")
    public void selectSauces() {

        new ButtonElement(sectionSaucesButtonLocator).clickButton();
    }

    @Step("Выбор раздела 'Начинки'")
    public void selectToppings() {

        new ButtonElement(sectionToppingsButtonLocator).clickButton();
    }

    @Step("Проверить, активен ли раздел 'Булки'")
    public boolean isActiveSectionBun() {
        return new ButtonElement(activeSectionBunLocator).isEnableButton();  // предполагаем, что элемент с id "activeSectionBun" появляется, когда раздел активен
    }
    @Step("Проверить, активен ли раздел 'Соусы'")
    public boolean isActiveSectionSauces() {
     return new ButtonElement(activeSectionSaucesLocator).isEnableButton();  // предполагаем, что элемент с id "activeSectionSauces" появляется, когда раздел активен
    }

    @Step("Проверить, активен ли раздел 'Начинки'")
    public boolean isActiveSectionToppings() {
        return new ButtonElement(activeSectionToppingsLocator).isEnableButton(); // предполагаем, что элемент с id "activeSectionToppings" появляется, когда раздел активен
    }

}
