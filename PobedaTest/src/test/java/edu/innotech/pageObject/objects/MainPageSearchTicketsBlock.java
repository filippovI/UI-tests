package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.pageObject.objects.ObjectsUtils.checkVisibleElements;

public class MainPageSearchTicketsBlock {

    SelenideElement searchTicketBlock = $("form:has(input[placeholder='Откуда'])");
    SelenideElement fromInput = $("form input[placeholder='Откуда'][autocorrect]");
    SelenideElement whereInput = $("form input[placeholder='Куда'][autocorrect]");
    SelenideElement dateThereInput = $(By.xpath("//form//input[@placeholder='Туда']/.."));
    SelenideElement dateBackInput = $(By.xpath("//form//input[@placeholder='Обратно']/.."));
    SelenideElement menuItem = $("div[class*='dp-ScrollArea-contentEl'] div[role='menuitem']:first-child");
    SelenideElement searchButton = $(By.xpath("//span[text()='Поиск']/.."));


    public MainPageSearchTicketsBlock checkTicketBlock() {
        searchTicketBlock.shouldBe(visible.because("Блок поиска билетов не появился"));
        return this;
    }

    public MainPageSearchTicketsBlock checkFieldsInBlock() {
        checkVisibleElements("поиска билетов", fromInput, whereInput, dateThereInput, dateBackInput);
        return this;
    }

    public MainPageSearchTicketsBlock fillFieldsFromAndWhere(String from, String where) {
        try {
            fromInput.setValue(Keys.CONTROL + "a" + Keys.BACK_SPACE + from);
            menuItem.shouldBe(visible).shouldHave(text(from)).click();
            whereInput.setValue(Keys.CONTROL + "a" + Keys.BACK_SPACE + where);
            menuItem.shouldBe(visible).shouldHave(text(where)).click();
            searchButton.click();
        } catch (ElementNotInteractableException ex) {
            throw new ElementNotInteractableException("Не удалось ввести данные в поля 'Откуда' и 'Куда'");
        }
        return this;
    }

    public MainPageSearchTicketsBlock checkBorder(SelenideElement element) {
        element.shouldHave(cssValue("border-color", "rgb(213, 0, 98)")
                .because("Цвет обводки поля Туда не красный"));
        return this;
    }

    public MainPageSearchTicketsBlock checkColorDateFromInput() {
        return checkTicketBlock()
                .checkFieldsInBlock()
                .fillFieldsFromAndWhere("Москва", "Санкт-Петербург")
                .checkBorder(dateThereInput);
    }
}
