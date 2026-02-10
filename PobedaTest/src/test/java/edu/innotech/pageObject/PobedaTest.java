package edu.innotech.pageObject;

import edu.innotech.pageObject.objects.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PobedaTest {
    private static final String POBEDA_URL = "https://pobeda.aero/";
    private static final Duration TIME_FOR_PAGE_LOAD_WAIT = Duration.ofSeconds(5);
    private static final Duration TIME_FOR_IMPLICITLY_WAIT = Duration.ofSeconds(3);
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(POBEDA_URL);
        driver.manage().timeouts().pageLoadTimeout(TIME_FOR_PAGE_LOAD_WAIT);
        driver.manage().timeouts().implicitlyWait(TIME_FOR_IMPLICITLY_WAIT);
        driver.manage().window().maximize();
    }

    @Test
    public void checkInformationBlock() {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .selectInformationBlock()
                .checkHeadlines();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
