package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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

    @Step("Проверяем видимость полей в блоке")
    public MainPageSearchTicketsBlock checkFieldsInBlock() {
        checkVisibleElements("поиска билетов", fromInput, whereInput, dateThereInput, dateBackInput, searchButton);
        return this;
    }

    @Step("Заполняем поля 'Откуда' и 'Куда' данными {from} - {where} и нажимаем 'Поиск'")
    public MainPageSearchTicketsBlock fillFieldsFromAndWhere(String from, String where) {
        fromInput.setValue(Keys.CONTROL + "a" + Keys.BACK_SPACE + from);
        menuItem.shouldBe(visible).shouldHave(text(from)).click();
        whereInput.setValue(Keys.CONTROL + "a" + Keys.BACK_SPACE + where);
        menuItem.shouldBe(visible).shouldHave(text(where)).click();
        searchButton.click();
        return this;
    }

    @Step("Проверяем обводку поля {element}")
    public MainPageSearchTicketsBlock checkBorder(SelenideElement element, String borderColor) {
        element.shouldHave(cssValue("border-color", borderColor)
                .because("Цвет обводки поля Туда не красный"));
        return this;
    }

    public MainPageSearchTicketsBlock checkColorDateFromInput(String borderColor) {
        return checkTicketBlock()
                .checkFieldsInBlock()
                .fillFieldsFromAndWhere("Москва", "Санкт-Петербург")
                .checkBorder(dateThereInput, borderColor);
    }
}
