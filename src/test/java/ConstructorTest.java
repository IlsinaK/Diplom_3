import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Проверка выбора раздела Булки")
    @Description("Тест проверяет, что раздел Булки становится активным после его выбора.")
    public void selectBunsTest() {
        constructorPage.selectSauces();
        constructorPage.selectBuns(); // Выбор раздела булки
        assertTrue("Раздел Булки не активен", constructorPage.isActiveSectionBun()); // Проверка, что раздел активен
    }

    @Test
    @DisplayName("Проверка выбора раздела Соусы")
    @Description("Тест проверяет, что раздел Соусы становится активным после его выбора.")
    public void selectSaucesTest() {
        constructorPage.selectSauces(); // Выбор раздела соусы
        assertTrue("Раздел Соусы не активен", constructorPage.isActiveSectionSauces()); // Проверка, что раздел активен
    }

    @Test
    @DisplayName("Проверка выбора раздела Начинки")
    @Description("Тест проверяет, что раздел Начинки становится активным после его выбора.")
    public void selectToppingsTest() {
        constructorPage.selectToppings(); // Выбор раздела начинки
        assertTrue("Раздел Начинки не активен", constructorPage.isActiveSectionToppings()); // Проверка, что раздел активен
    }

    @After
    public void tearDown() {

        closeWebDriver(); // Закрываем браузер
    }
}

