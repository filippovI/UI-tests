package edu.innotech.pageObject.objects;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class MainPageInformationBlock {

    @FindBy(css = "a[href*='#flight']")
    WebElement preparingForFlyHead;

    @FindBy(css = "a[href*='#useful']")
    WebElement usefulInformationHead;

    @FindBy(css = "a[href*='#company']")
    WebElement aboutCompanyHead;

    WebDriver driver;

    public MainPageInformationBlock(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPageInformationBlock checkHeadlines() {
        SoftAssertions softAssertions = new SoftAssertions();
        Map<WebElement, String> headLines = Map.of(
                preparingForFlyHead, "Подготовка к полёту",
                usefulInformationHead, "Полезная информация",
                aboutCompanyHead, "О компании"
        );

        Assertions.assertTrue(preparingForFlyHead.isDisplayed()
                && usefulInformationHead.isDisplayed()
                && aboutCompanyHead.isDisplayed(), "В блоке информации не отобразились все элементы");

        softAssertions.assertThat(preparingForFlyHead.getText())
                .as("Блок информации должен содержать заголовок " + headLines.get(preparingForFlyHead))
                .isEqualTo(headLines.get(preparingForFlyHead));
        softAssertions.assertThat(usefulInformationHead.getText())
                .as("Блок информации должен содержать заголовок " + headLines.get(usefulInformationHead))
                .isEqualTo(headLines.get(usefulInformationHead));
        softAssertions.assertThat(aboutCompanyHead.getText())
                .as("Блок информации должен содержать заголовок " + headLines.get(aboutCompanyHead))
                .isEqualTo(headLines.get(aboutCompanyHead));
        softAssertions.assertAll();

        return this;
    }
}
