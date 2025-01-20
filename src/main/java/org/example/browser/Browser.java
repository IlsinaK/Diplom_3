package org.example.browser;

import com.codeborne.selenide.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser {

    public static void initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));

        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("browserProperty: " + browserProperty); // Отладочный вывод

        BrowserType browserType = BrowserType.valueOf(browserProperty); // Получение типа браузера
        switch (browserType) {
            case CHROME:
                Configuration.browser = "chrome";

                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "D:/test/yandexdriver/yandexdriver.exe");
                Configuration.browser = "chrome";
                break;
            default:
                throw new RuntimeException("Browser undefined: " + browserProperty);
        }
    }
}
