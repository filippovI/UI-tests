package edu.innotech;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    }

    @Test
    public void CheckLoginForInvalidUserAndPassword() {
        Assertions.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", driver.getTitle());

        //Открываем модальное окно и проверяем все элементы
        driver.findElement(XPATH_FOR_LOGIN_BUTTON.getLocator()).click();
        driver.findElement(CSS_FOR_MODAL_AUTH.getLocator()).isDisplayed();
        driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).isDisplayed();
        driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).isDisplayed();
        driver.findElement(XPATH_FOR_MODAL_BUTTON_LOGIN.getLocator()).isDisplayed();

        //Вводим логин и пароль. Нажимаем "Войти"
        driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).click();
        driver.findElement(CSS_FOR_MODAL_INPUT_LOGIN.getLocator()).sendKeys("Qwerty");
        driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).click();
        driver.findElement(CSS_FOR_MODAL_INPUT_PASSWORD.getLocator()).sendKeys("Qwerty");
        driver.findElement(XPATH_FOR_MODAL_BUTTON_LOGIN.getLocator()).click();

        //Ждем 2 секунды и проверяем сообщение об ошибке
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(XPATH_FOR_ERROR_MESSAGE.getLocator()).isDisplayed();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
