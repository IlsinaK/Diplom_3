import io.qameta.allure.Step;
import org.example.pageobject.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.pageobject.ConstructorPage.CONSTRUCTOR_PAGE_URL;
import static org.junit.Assert.assertTrue;


public class ConstructorTest extends BaseUITest {
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        open(CONSTRUCTOR_PAGE_URL);
        constructorPage = new ConstructorPage();
    }

    @Test
    @Step("Проверка выбора раздела Булки")
    public void selectBunsTest() {
        constructorPage.selectSauces();
        constructorPage.selectBuns(); // Выбор раздела булки
        assertTrue("Раздел Булки не активен", constructorPage.isActiveSectionBun()); // Проверка, что раздел активен
    }

    @Test
    @Step("Проверка выбора раздела Соусы")
    public void selectSaucesTest() {
        constructorPage.selectSauces(); // Выбор раздела соусы
        assertTrue("Раздел Соусы не активен", constructorPage.isActiveSectionSauces()); // Проверка, что раздел активен
    }

    @Test
    @Step("Проверка выбора раздела Начинки")
    public void selectToppingsTest() {
        constructorPage.selectToppings(); // Выбор раздела начинки
        assertTrue("Раздел Начинки не активен", constructorPage.isActiveSectionToppings()); // Проверка, что раздел активен
    }

    @After
    public void tearDown() {
        closeWebDriver(); // Закрываем браузер
    }
}

