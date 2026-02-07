package edu.innotech.pageObject.objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class MainPageReservationBlock {
    @FindBy(css = "input[placeholder*='Фамилия']")
    WebElement surnameInput;
    @FindBy(css = "input[placeholder*='бронирования']")
    WebElement reservationNumberInput;
    @FindBy(xpath = "//button[span[text()='Поиск']]")
    WebElement searchButton;

    WebDriver driver;
    public MainPageReservationBlock(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPageReservationBlock checkElements() {
        await().atMost(3, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .ignoreNoExceptions()
                .until(() -> surnameInput.isDisplayed() && reservationNumberInput.isDisplayed() && searchButton.isDisplayed());
        return this;
    }

    public PageResultOfSearchReservation fillIncorrectData() {
        checkElements();
        surnameInput.click();
        surnameInput.sendKeys("Qwerty");
        reservationNumberInput.click();
        reservationNumberInput.sendKeys("XXXXXX");
        searchButton.click();
        return new PageResultOfSearchReservation(driver, driver.getWindowHandle());
    }
}
