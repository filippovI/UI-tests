package edu.innotech.pageObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PobedaTest extends BasePobedaTest {

    @Test
    @DisplayName("Проверяем блок информации на заголовки")
    public void checkInformationBlock() {
        mainPage
                .checkTitleAndImage()
                .selectInformationBlock()
                .checkHeadlines();
    }

    @Test
    @DisplayName("Проверяем блок поиска билетов на поля, кнопки и обводку поля при неудачном поиске")
    public void checkSearchBlock() {
        mainPage
                .checkTitleAndImage()
                .selectSearchBlock()
                .checkColorDateFromInput();
    }

    @Test
    @DisplayName("Проверяем результат поиска несуществующего бронирования")
    public void checkResultOfSearchReservation() {
        mainPage
                .checkTitleAndImage()
                .selectReservationBlock()
                .fillIncorrectData("QWERTY", "XXXXXX")
                .checkErrorMessage();
    }
}
