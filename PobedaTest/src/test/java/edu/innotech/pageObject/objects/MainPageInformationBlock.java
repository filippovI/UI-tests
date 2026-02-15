package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.pageObject.objects.ObjectsUtils.checkVisibleElements;

public class MainPageInformationBlock {

    SelenideElement preparingForFlyHead = $("a[href*='#flight']");
    SelenideElement usefulInformationHead = $("a[href*='#useful']");
    SelenideElement aboutCompanyHead = $("a[href*='#company']");

    @Step("Проверяем, что элементы существуют на сайте и что заголовки указаны верно")
    public MainPageInformationBlock checkHeadlines() {
        checkVisibleElements("информации", preparingForFlyHead, usefulInformationHead, aboutCompanyHead);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(preparingForFlyHead.getText())
                .as("В блоке информации должен быть заголовок Подготовка к полёту")
                .isEqualTo("Подготовка к полёту");
        softAssertions.assertThat(usefulInformationHead.getText())
                .as("В блоке информации должен быть заголовок Полезная информация")
                .isEqualTo("Полезная информация");
        softAssertions.assertThat(aboutCompanyHead.getText())
                .as("В блоке информации должен быть заголовок О компании")
                .isEqualTo("О компании");
        softAssertions.assertAll();
        return this;
    }
}
