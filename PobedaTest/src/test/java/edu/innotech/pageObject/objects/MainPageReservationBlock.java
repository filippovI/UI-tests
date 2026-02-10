package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.pageObject.objects.ObjectsUtils.checkVisibleElements;

public class MainPageReservationBlock {
    SelenideElement surnameInput = $("input[placeholder*='Фамилия']");
    SelenideElement reservationNumberInput = $("input[placeholder*='бронирования']");
    SelenideElement searchButton = $(By.xpath("//button[span[text()='Поиск']]"));

    public MainPageReservationBlock checkElements() {
        checkVisibleElements("бронирования", surnameInput, reservationNumberInput, searchButton);
        return this;
    }

    public PageResultOfSearchReservation fillIncorrectData() {
        checkElements();
        surnameInput.val("Qwerty");
        reservationNumberInput.val("XXXXXX");
        searchButton.click();
        return new PageResultOfSearchReservation();
    }
}
