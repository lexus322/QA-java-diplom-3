import org.junit.Before;

import com.codeborne.selenide.Configuration;

public class BaseTest {
    @Before
    public void configureDriver(){
        Configuration.browserSize = "1920x1080";
    }
}
