package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.pageObject.objects.ObjectsUtils.checkVisibleElements;

public class MainPageReservationBlock {
    SelenideElement surnameInput = $("input[placeholder*='Фамилия']");
    SelenideElement reservationNumberInput = $("input[placeholder*='бронирования']");
    SelenideElement searchButton = $(By.xpath("//button[span[text()='Поиск']]"));

    @Step("Проверяем видимость элементов в блоке")
    public MainPageReservationBlock checkElements() {
        checkVisibleElements("бронирования", surnameInput, reservationNumberInput, searchButton);
        return this;
    }

    @Step("Заполняем поля 'Фамилия' и 'Номер бронирования' некорректными данными {surname} - {resNum} ")
    public PageResultOfSearchReservation fillIncorrectData(String surname, String resNum) {
        checkElements();
        surnameInput.val(surname);
        reservationNumberInput.val(resNum);
        searchButton.click();
        return new PageResultOfSearchReservation();
    }
}
