package edu.innotech;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public enum Locators {
    XPATH_FOR_LOGIN_BUTTON(By.xpath("//div[@class='header-right-menu']//button[contains(text(),'Войти')]")),
    CSS_FOR_MODAL_AUTH(By.cssSelector("div[class='auth-modal']")),
    CSS_FOR_MODAL_INPUT_LOGIN(By.cssSelector("div[class='auth-modal'] input[placeholder='Логин']")),
    CSS_FOR_MODAL_INPUT_PASSWORD(By.cssSelector("div[class='auth-modal'] input[placeholder='Пароль']")),
    XPATH_FOR_MODAL_BUTTON_LOGIN(By.xpath("//div[@class='auth-modal']//span[text()='Войти']/..")),
    XPATH_FOR_ERROR_MESSAGE(By.xpath("//span[text()='Ошибка. Вы ввели неверные данные авторизации']"));

    private final By locator;

    Locators(By xpath) {
        locator = xpath;
    }
}
