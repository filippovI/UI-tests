package edu.innotech.pageObject.objects;

import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static edu.innotech.pageObject.objects.ObjectsUtils.waiting;

public class MainPage {

    private final String title = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
            "прямые и трансферные рейсы с пересадками";

    @FindBy(xpath = "//button[span[text()='Управление бронированием']]")
    WebElement buttonForReservationBlock;

    @FindBy(css = "a[href='/information']")
    WebElement informationButton;

    @FindBy(xpath = "//img[contains(@src, 'logo-rus-white.')]/ancestor::div[contains(@class, 'root')][1]")
    WebElement whiteLogo;

    @FindBy(xpath = "//img[contains(@src, 'logo-rus.')]/ancestor::div[contains(@class, 'root')][1]")
    WebElement logo;

    @FindBy(css = "div[role='dialog']")
    WebElement adsDialog;

    @FindBy(css = "button[data-testid='ads-popup-close-icon']")
    WebElement closeButtonForAdsDialog;

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage checkAds(Duration duration) {
        try {
            waiting(() -> {
                if (adsDialog.isDisplayed()) closeButtonForAdsDialog.click();
                return true;
            }, duration);
        } catch (NoSuchElementException | ConditionTimeoutException ignored) {}
        return this;
    }

    public MainPage checkVisibleLogo(Duration duration) {
        try {
            waiting(() -> logo.isDisplayed() || whiteLogo.isDisplayed(), duration);
        } catch (NoSuchElementException ex) {
            throw new java.util.NoSuchElementException("Лого Победы не появилось");
        }
        return this;
    }

    public MainPage checkTitle() {
        Assertions.assertEquals(title, driver.getTitle(), "Неверный заголовок");
        return this;
    }

    public MainPageInformationBlock selectInformationBlock() {
        Actions action = new Actions(driver);
        action.moveToElement(informationButton).perform();
        return new MainPageInformationBlock(driver);
    }

    public MainPageSearchTicketsBlock selectSearchBlock() {
        return new MainPageSearchTicketsBlock(driver);
    }

    public MainPageReservationBlock selectReservationBlock() {
        buttonForReservationBlock.click();
        return new MainPageReservationBlock(driver);
    }

    public MainPage checkTitleAndImage() {
        return checkVisibleLogo(Duration.ofSeconds(2))
                .checkAds(Duration.ofSeconds(2))
                .checkTitle();
    }

}
