import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.LogInPage;
import ru.tiurin.pageobject.RegPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class RegTest extends BaseTest {
    private final List<User> userListForDelete = new ArrayList<>();

    @Test
    @DisplayName("Проверка регистрации нового пользователя с валидными данными")
    public void checkNewUserIsRegWithValidData() throws InterruptedException {
        User user = User.getRandomUserValidData();
        open(RegPage.URL, RegPage.class)
                .fillNameInput(user.getName())
                .fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickButtonReg()
                .regPageDisappear();
        userListForDelete.add(user);
        String currentURL = webdriver().driver().url();
        assertEquals(LogInPage.URL, currentURL);

    }


    @After
    @DisplayName("Удаление пользователя и очистка cookies")
    public void cleanDate() {
        for (User user : userListForDelete) {
            if (user != null) {
                user.deleteUser();
            }
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
