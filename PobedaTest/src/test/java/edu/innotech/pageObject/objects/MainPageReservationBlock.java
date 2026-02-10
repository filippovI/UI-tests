package edu.innotech.pageObject.objects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static edu.innotech.pageObject.objects.ObjectsUtils.waiting;

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

    public MainPageReservationBlock checkElements(Duration duration) {
        try {
            waiting(() -> surnameInput.isDisplayed() && reservationNumberInput.isDisplayed() && searchButton.isDisplayed(),
                    duration);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Элементы блока бронирования не появились");
        }
        return this;
    }

    public PageResultOfSearchReservation fillIncorrectData(String surname, String reservationNumber) {
        checkElements(Duration.ofSeconds(3));
        surnameInput.click();
        surnameInput.sendKeys(surname);
        reservationNumberInput.click();
        reservationNumberInput.sendKeys(reservationNumber);
        searchButton.click();
        return new PageResultOfSearchReservation(driver, driver.getWindowHandle());
    }
}
