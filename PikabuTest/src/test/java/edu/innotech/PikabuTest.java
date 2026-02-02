package edu.innotech;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static edu.innotech.Locators.*;

public class PikabuTest {
    private final String PIKABU_URL = "https://pikabu.ru/";
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(PIKABU_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    @DisplayName("Проверка входа с некорректными логином и паролем")
    public void CheckLoginForInvalidUserAndPassword() {
        Assertions.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", driver.getTitle());

        //Открываем модальное окно и проверяем все элементы
        driver.findElement(XPATH_FOR_LOGIN_BUTTON.getLocator()).click();
        Assertions.assertTrue(driver.findElement(CSS_FOR_MODAL_AUTH.getLocator()).isDisplayed(),
                "Модальное окно не отображается");
        Assertions.assertTrue(driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).isDisplayed(),
                "Поле ввода логина в модальном окне не отображается");
        Assertions.assertTrue(driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).isDisplayed(),
                "Поле ввода пароля в модальном окне не отображается");
        Assertions.assertTrue( driver.findElement(XPATH_FOR_MODAL_BUTTON_LOGIN.getLocator()).isDisplayed(),
                "Кнопка 'Войти' в модальном окне не отображается");

        //Вводим логин и пароль. Нажимаем "Войти"
        driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).click();
        driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).sendKeys("Qwerty");
        driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).click();
        driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).sendKeys("Qwerty");
        driver.findElement(XPATH_FOR_MODAL_BUTTON_LOGIN.getLocator()).click();

        //Проверяем сообщение об ошибке
        Assertions.assertTrue(driver.findElement(XPATH_FOR_ERROR_MESSAGE.getLocator()).isDisplayed(),
                "Сообщение об ошибке после неудачного входа не отображается");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}