package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PageResultOfSearchReservation {

    SelenideElement checkBox = $("div.customCheckbox");
    SelenideElement searchButton = $("button[class*='search']");
    SelenideElement errorMessage = $(By.xpath("//div[text()='Заказ с указанными параметрами не найден']"));

    public PageResultOfSearchReservation waitLoadPage() {
        switchTo().window(1);
        for (int i = 0; true; i++) {
            try {
                checkBox.shouldBe(visible);
                searchButton.shouldBe(visible);
                return this;
            } catch (UIAssertionError e) {
                if (i == 3) throw new AssertionError("Не удалось загрузить страницу");
                refresh();
            }
        }
    }

    public PageResultOfSearchReservation clickCheckBoxAndButton() {
        checkBox.click();
        searchButton.click();
        return this;
    }

    public PageResultOfSearchReservation locateErrorMessage() {
        errorMessage.shouldBe(visible.because("Сообщение с ошибкой не появилось"))
                .shouldHave(text("Заказ с указанными параметрами не найден").because("Текст ошибки неверный"));
        return this;
    }

    public PageResultOfSearchReservation checkErrorMessage() {
        return waitLoadPage()
                .clickCheckBoxAndButton()
                .locateErrorMessage();
    }
}
