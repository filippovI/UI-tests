package edu.innotech.selenuimWD;

import org.awaitility.core.ConditionTimeoutException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static edu.innotech.selenuimWD.Locators.*;
import static edu.innotech.selenuimWD.PobedaTest.driver;
import static org.awaitility.Awaitility.await;

public class PobedaTestUtils {

    public static void waitKaliningradImage(Duration duration) {
        try {
            WebElement imageElement = driver.findElement(CSS_FOR_KALININGRAD_IMAGE.getLocator());
            waiting(() -> {
                if (imageElement.isDisplayed()) return true;
                driver.findElement(CSS_FOR_BACK_BUTTON.getLocator()).click();
                return false;
            }, duration);
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException("Картинка с Калининградом не появилась на сайте\n" + e.getMessage());
        }
    }

    public static @NotNull String getTextFromKaliningradImage(Duration duration) {
        try {
            WebElement textImageElement = driver.findElement(CSS_FOR_KALININGRAD_TEXT.getLocator());
            waiting(textImageElement::isDisplayed, duration);
            return textImageElement.getText();
        } catch (ElementNotInteractableException ex) {
            throw new NoSuchElementException("Не удалось найти текст для картинки\n" + ex.getMessage());
        }
    }

    public static void clickLanguageButton() {
        try {
            WebElement languageButton = driver.findElement(XPATH_FOR_LANGUAGE_BUTTON.getLocator());
            languageButton.click();
            driver.findElement(XPATH_FOR_LANGUAGE_BUTTON.getLocator());
            driver.findElement(XPATH_FOR_ENGLISH_BUTTON.getLocator()).click();
        } catch (ElementNotInteractableException ex) {
            throw new ElementNotInteractableException("Не удалось кликнуть на кнопку смены языка\n" + ex.getMessage());
        }
    }

    public static boolean checkEnglishMenu(Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            boolean ticketSearch = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    XPATH_FOR_TICKET_SEARCH.getLocator(), "Ticket search"));
            boolean onlineCheckIn = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    XPATH_FOR_ONLINE_CHECK_IN.getLocator(), "Online check-in"));
            boolean manageMyBooking = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    XPATH_FOR_MANAGE_MY_BOOKING.getLocator(), "Manage my booking"));
            return ticketSearch && onlineCheckIn && manageMyBooking;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Не удалось найти пункты меню на английском");
        }
    }

    private static void waiting(Callable<Boolean> condition, Duration duration) {
        await().alias("Awaitility waiting")
                .atMost(duration)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .ignoreNoExceptions()
                .until(condition);
    }
}