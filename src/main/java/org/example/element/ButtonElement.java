package org.example.element;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class ButtonElement {

    SelenideElement buttonElement;

    public ButtonElement(String locator){

        buttonElement = $(new By.ByXPath(locator));
    }

    @Step("Нажать на кнопку")
    public void clickButton(){
        buttonElement.shouldBe(enabled);
        buttonElement.scrollIntoView(true);
        buttonElement.click();
    }

    @Step("Доступна ли кнопка")
    public boolean isEnableButton(){
        return buttonElement.isEnabled();
    }
}
