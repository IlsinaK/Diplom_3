package org.example.element;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class ButtonElement {

    SelenideElement buttonElement;

    public ButtonElement(String locator){
        buttonElement = $(new By.ByXPath(locator));
    }

    public void clickButton(){
        buttonElement.shouldBe(enabled);
        buttonElement.scrollIntoView(true);
        buttonElement.click();
    }

    public void cscrollAndCickButton(){
        buttonElement.shouldBe(enabled);
        buttonElement.scrollIntoView(false);
        buttonElement.click();
    }

    public boolean isEnableButton(){
        return buttonElement.isEnabled();
    }
}
