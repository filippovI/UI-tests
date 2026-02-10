package edu.innotech.pageObject.objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public PageResultOfSearchReservation waitLoadPage() {
        //Страница грузится не с 1 раза, поэтому пробуем 3 раза или выкидываем исключение.
        //Может выскочить капча
        int refreshCount = 0;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                this.driver.switchTo().window(windowHandle);
                break;
            }
        }
        while (refreshCount < 4) {
            try {
                Assertions.assertTrue(checkBox.isDisplayed() && searchButton.isDisplayed(),
                        "Чек бокс и кнопка поиска не отобразились");
                break;
            } catch (NoSuchElementException | TimeoutException ex) {
                if (refreshCount == 3) throw new NoSuchElementException("Не удалось загрузить страницу");
                this.driver.navigate().refresh();
                refreshCount++;
            }
        }
        return this;
    }

    public PageResultOfSearchReservation clickCheckBoxAndButton() {
        checkBox.click();
        searchButton.click();
        return this;
    }

    public PageResultOfSearchReservation locateErrorMessage() {
        Assertions.assertTrue(errorMessage.isDisplayed(), "Ошибка бронирования не отобразилась");
        return this;
    }

    public PageResultOfSearchReservation checkErrorMessage() {
        return waitLoadPage()
                .clickCheckBoxAndButton()
                .locateErrorMessage();
    }
}
