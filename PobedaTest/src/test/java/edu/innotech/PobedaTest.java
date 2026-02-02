package edu.innotech;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PobedaTest {
    private static final String GOOGLE_URL = "https://google.com/";
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(GOOGLE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    @DisplayName("Проверка входа с некорректными логином и паролем")
    public void CheckLoginForInvalidUserAndPassword() {

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}