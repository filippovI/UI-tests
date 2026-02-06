package edu.innotech.pageObject.objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        Assertions.assertTrue(preparingForFlyHead.isDisplayed()
                && usefulInformationHead.isDisplayed()
                && aboutCompanyHead.isDisplayed());
        return this;

    }
}
