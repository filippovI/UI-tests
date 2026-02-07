package edu.innotech.pageObject.objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class MainPage {
    private final String title = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
            "прямые и трансферные рейсы с пересадками";
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

    public MainPage checkAds() {
        await().pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() ->
                {
                    if (adsDialog.isDisplayed())
                        closeButtonForAdsDialog.click();
                    return true;
                });
        return this;
    }

    public MainPage checkVisibleLogo(Duration duration) {
        try {
            await()
                    .atMost(duration)
                    .pollInterval(500, TimeUnit.MILLISECONDS)
                    .ignoreNoExceptions()
                    .until(() -> logo.isDisplayed() || whiteLogo.isDisplayed());
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

    public MainPage checkTitleAndImage() {
        return checkVisibleLogo(Duration.ofSeconds(2))
                .checkAds()
                .checkTitle();
    }

}
