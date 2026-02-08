package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;

import static com.codeborne.selenide.Condition.visible;

public class ObjectsUtils {

    public static void checkVisibleElements(String block, SelenideElement... elements ) {
        try {
            for (SelenideElement element : elements) element.shouldBe(visible);
        } catch (UIAssertionError ex) {
            throw new AssertionError("Элементы блока " + block + " не появились");
        }
    }
}
