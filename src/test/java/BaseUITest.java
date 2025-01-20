
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.example.browser.Browser.initDriver;

public class BaseUITest {

    @Before
    public void startUp() throws IOException{
        initDriver();
        Configuration.timeout = 4000;
    }

    @After
    public void tearDown(){

        closeWebDriver();
    }
}
