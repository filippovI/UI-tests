package edu.innotech;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public enum Locators {
    CSS_FOR_KALININGRAD_IMAGE(By.cssSelector("img[src*='KALINIGRAD']")),
    CSS_FOR_KALININGRAD_TEXT(By.cssSelector("img[src*='KALINIGRAD'] ~ div:nth-last-child(1) > div > div:nth-child(1)")),
    CSS_FOR_BACK_BUTTON(By.cssSelector("button[aria-label='Назад']")),
    XPATH_FOR_LANGUAGE_BUTTON(By.xpath("//button[contains(text(), 'РУС')]")),
    XPATH_FOR_ENGLISH_BUTTON(By.xpath("//div[@role='menuitem' and contains(text(), 'English')]")),
    XPATH_FOR_TICKET_SEARCH(By.xpath("//button[.//span[contains(text(), 'search')]]")),
    XPATH_FOR_ONLINE_CHECK_IN(By.xpath("//button[.//span[contains(text(), 'check-in')]]")),
    XPATH_FOR_MANAGE_MY_BOOKING(By.xpath("//button[.//span[contains(text(), 'booking')]]"));

    private final By locator;

    Locators(By xpath) {
        locator = xpath;
    }
}
