package org.example.element;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class InputElement {
    private SelenideElement inputElement;

    public InputElement(String locator) {

        inputElement = $(new By.ByXPath(locator));
    }

    @Step("Очистить и установить значение")
    public void clearAndSetValue(String inputValue) {
        inputElement.shouldBe(enabled).clear();
        inputElement.setValue(inputValue);
    }

    @Step("Очистить и установить значение")
    public void setValue(String inputValue) {
        inputElement.shouldBe(enabled);
        inputElement.setValue(inputValue);
    }
}

