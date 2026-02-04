package edu.innotech;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static edu.innotech.PobedaTestUtils.*;

public class PobedaTest {
    private static final String POBEDA_URL = "https://pobeda.aero/";
    private static final Duration TIME_FOR_WAITING_IMAGE_SEC = Duration.ofSeconds(5);
    private static final Duration TIME_FOR_WAITING_TEXT_SEC = Duration.ofSeconds(2);
    private static final Duration TIME_FOR_WAITING_MENU_TEXT_SEC = Duration.ofSeconds(2);
    private static final Duration TIME_FOR_PAGE_LOAD_WAIT = Duration.ofSeconds(10);
    private static final Duration TIME_FOR_IMPLICITLY_WAIT = Duration.ofSeconds(3);

    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.get(POBEDA_URL);
        driver.manage().timeouts().pageLoadTimeout(TIME_FOR_PAGE_LOAD_WAIT);
        driver.manage().timeouts().implicitlyWait(TIME_FOR_IMPLICITLY_WAIT);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка картинки 'Полетели в Калининград'")
    public void checkTopImagineAndText() {
        waitKaliningradImage(TIME_FOR_WAITING_IMAGE_SEC);
        Assertions.assertEquals("Полетели в Калининград!", getTextFromKaliningradImage(TIME_FOR_WAITING_TEXT_SEC),
                "Текст на картинке не совпадает с текстом 'Полетели в Калининград!'");
    }

    @Test
    @DisplayName("Проверка пунктов меню после смены языка")
    public void checkMenuAfterChangeLanguage() {
        clickLanguageButton();
        Assertions.assertTrue(checkEnglishMenu(TIME_FOR_WAITING_MENU_TEXT_SEC));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}