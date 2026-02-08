package edu.innotech.pageObject;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import edu.innotech.pageObject.objects.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.open;

public class BasePobedaTest {

    private static final String POBEDA_URL = "https://pobeda.aero/";
    MainPage mainPage;

    @BeforeEach
    public void setUp() {
        open(POBEDA_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        mainPage = new MainPage();
        ChromeOptions options = new ChromeOptions();
        // Убираем флаг "nand-driver" и автоматизацию в navigator.webdriver
        options.addArguments("--disable-blink-features=AutomationControlled");
        // Убираем уведомление "Браузером управляет автоматизированное ПО"
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        // Добавляем реальный User-Agent (замените на актуальный для вашей системы)
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        Configuration.browserCapabilities = options;
    }
}
