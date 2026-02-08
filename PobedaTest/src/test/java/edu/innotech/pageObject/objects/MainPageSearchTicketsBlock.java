package edu.innotech.pageObject.objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static edu.innotech.pageObject.objects.ObjectsUtils.waiting;

public class MainPageSearchTicketsBlock {

    @FindBy(css = "form:has(input[placeholder='Откуда'])")
    WebElement searchTicketBlock;
    @FindBy(css = "form input[placeholder='Откуда'][autocorrect]")
    WebElement fromInput;
    @FindBy(css = "form input[placeholder='Куда'][autocorrect]")
    WebElement whereInput;
    @FindBy(xpath = "//form//input[@placeholder='Туда']/..")
    WebElement dateFromInput;
    @FindBy(xpath = "//form//input[@placeholder='Обратно']/..")
    WebElement dateBackInput;
    @FindBy(css = "div[class*='dp-ScrollArea-contentEl'] div[role='menuitem']:first-child")
    WebElement menuItem;
    @FindBy(xpath = "//span[text()='Поиск']/..")
    WebElement searchButton;

    WebDriver driver;

    public MainPageSearchTicketsBlock(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPageSearchTicketsBlock checkTicketBlock() {
        Assertions.assertTrue(searchTicketBlock.isDisplayed(), "Блок покупки билетов не отобразился");
        return this;
    }

    public MainPageSearchTicketsBlock checkFieldsInBlock() {
        Assertions.assertTrue(fromInput.isDisplayed()
                && whereInput.isDisplayed()
                && dateFromInput.isDisplayed()
                && dateBackInput.isDisplayed(), "Не все поля отобразились в блоке поиска билетов");
        return this;
    }

    public MainPageSearchTicketsBlock fillFieldsFromAndWhere(String from, String where) {
        try {
            fromInput.click();
            fromInput.sendKeys(from);
            waiting(() -> menuItem.isDisplayed(), Duration.ofSeconds(1));
            menuItem.click();
            whereInput.click();
            whereInput.sendKeys(where);
            waiting(() -> menuItem.isDisplayed(), Duration.ofSeconds(1));
            menuItem.click();
            searchButton.click();
        } catch (ElementNotInteractableException ex) {
            throw new ElementNotInteractableException("Не удалось ввести данные в поля 'Откуда' и 'Куда'");
        }
        return this;
    }

    public MainPageSearchTicketsBlock checkBorder(WebElement element) {
        waiting(element::isDisplayed, Duration.ofSeconds(1));
        String test = element.getCssValue("border-color");
        Assertions.assertTrue(test.contains("213, 0, 98"), "Цвет обводки поля Туда отличает от красного");
        return this;
    }

    public MainPageSearchTicketsBlock checkColorDateFromInput() {
        return checkTicketBlock()
                .checkFieldsInBlock()
                .fillFieldsFromAndWhere("Москва", "Санкт-Петербург")
                .checkBorder(dateFromInput);
    }
}
