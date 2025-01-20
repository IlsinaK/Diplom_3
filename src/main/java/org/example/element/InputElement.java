package org.example.element;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class InputElement {
    private SelenideElement inputElement;

    public InputElement(String locator) {
        inputElement = $(new By.ByXPath(locator));
    }

    public InputElement(SelenideElement selenideElement) {
        inputElement = selenideElement;
    }

    // Метод для нажатия на элемент и ввода значения
    public void clickAndSetValue(String inputValue) {
        inputElement.shouldBe(enabled).click(); // Нажимаем на элемент
        inputElement.clear(); // Очищаем
        inputElement.setValue(inputValue); // Устанавливаем значение
    }

    public void clearAndSetValue(String inputValue) {
        inputElement.shouldBe(enabled).clear();
        inputElement.setValue(inputValue);
    }

    public void setValueIfEmpty(String value) {
        if (inputElement.getValue().isEmpty()) {
            inputElement.setValue(value);
        }
    }
}

