package edu.innotech.pageObject;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Победа")
@DisplayName("Тесты сайта авиакомпании Победа")
public class PobedaTest extends BasePobedaTest {


    @Test
    @DisplayName("Проверяем блок информации на заголовки")
    @Feature("Проверяем блок информации")
    @Description("Заголовки в блоке 'Информация' должны быть видны и называться корректно")
    public void checkInformationBlock() {
        mainPage
                .checkTitleAndImage()
                .selectInformationBlock()
                .checkHeadlines();
    }

    @Test
    @DisplayName("Проверяем блок поиска билетов на поля, кнопки и обводку поля при неудачном поиске")
    @Feature("Проверяем блок поиска билетов")
    @Description("Все элементы в блоке должны быть видны. При неудачном поиске билета поле обводится красным контуром")
    public void checkSearchBlock() {
        mainPage
                .checkTitleAndImage()
                .selectSearchBlock()
                .checkColorDateFromInput("rgb(213, 0, 00)"); //"rgb(213, 0, 98)"
    }

    @Test
    @DisplayName("Проверяем результат поиска несуществующего бронирования")
    @Feature("Проверяем результат поиска бронирования")
    @Description("Всех элементы в блоке должны быть видны. При неудачном поиске бронирования должна появиться ошибка")
    public void checkResultOfSearchReservation() {
        mainPage
                .checkTitleAndImage()
                .selectReservationBlock()
                .fillIncorrectData("Qwerty", "XXXXXX")
                .checkErrorMessage();
    }
}
