package edu.innotech.pageObject.objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final String title = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
            "прямые и трансферные рейсы с пересадками";
    SelenideElement buttonForReservationBlock = $(By.xpath("//button[span[text()='Управление бронированием']]"));
    SelenideElement informationButton = $("a[href='/information']");
    SelenideElement whiteLogo = $(By.xpath("//img[contains(@src, 'logo-rus-white.')]/ancestor::div[contains(@class, 'root')][1]"));
    SelenideElement logo = $(By.xpath("//img[contains(@src, 'logo-rus.')]/ancestor::div[contains(@class, 'root')][1]"));
    SelenideElement adsDialog = $("div[role='dialog']");
    SelenideElement closeButtonForAdsDialog = $("button[data-testid='ads-popup-close-icon']");

    @Step("Закрываем рекламу, если есть")
    public MainPage checkAds() {
        try {
            adsDialog.shouldBe(visible, Duration.ofSeconds(2));
            closeButtonForAdsDialog.click();
        } catch (com.codeborne.selenide.ex.UIAssertionError ignored) {
        }
        return this;
    }

    @Step("Проверяем видимость лого на главной странице сайта")
    public MainPage checkVisibleLogo() {
        $$(List.of(logo, whiteLogo)).filter(visible).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверяем корректность заголовка в браузере")
    public MainPage checkTitle() {
        Assertions.assertEquals(title, Selenide.title(), "Неверный заголовок");
        return this;
    }

    @Step("Наводим мышку на блок информации")
    public MainPageInformationBlock selectInformationBlock() {
        informationButton.hover();
        return new MainPageInformationBlock();
    }

    @Step("Выбираем блок поиска билетов")
    public MainPageSearchTicketsBlock selectSearchBlock() {
        return new MainPageSearchTicketsBlock();
    }
    @Step("Выбираем блок управления бронированием")
    public MainPageReservationBlock selectReservationBlock() {
        buttonForReservationBlock.click();
        return new MainPageReservationBlock();
    }

    public MainPage checkTitleAndImage() {
        return checkVisibleLogo()
                .checkAds()
                .checkTitle();
    }

}
