package edu.innotech.pageObject.objects;

import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class PageResultOfSearchReservation {

    @FindBy(css = "div.customCheckbox")
    WebElement checkBox;
    @FindBy(css = "button[class*='search']")
    WebElement searchButton;
    @FindBy(xpath = "//div[text()='Заказ с указанными параметрами не найден']")
    WebElement errorMessage;

    WebDriver driver;
    String originalWindow;

    public PageResultOfSearchReservation(WebDriver driver, String originalWindow) {
        this.driver = driver;
        this.originalWindow = originalWindow;
        PageFactory.initElements(driver, this);
    }

    public PageResultOfSearchReservation checkElements() {
        try {
            await().atMost(3, TimeUnit.SECONDS)
                    .pollInterval(500, TimeUnit.MILLISECONDS)
                    .until(() -> checkBox.isDisplayed() && searchButton.isDisplayed());
        } catch (ConditionTimeoutException ex) {
            throw new ConditionTimeoutException("Элементы не отобразились на странице");
        }

        return this;
    }

    public PageResultOfSearchReservation checkErrorMessage() {
        checkElements();
        System.out.println("Кликаем на чек бокс");
        checkBox.click();
        System.out.println("Кликаем на кнопку");
        searchButton.click();
        Assertions.assertTrue(errorMessage.isDisplayed(), "Сообщение о неверной бронировании не отображается");
        return this;
    }
}
