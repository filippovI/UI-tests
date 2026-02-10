package edu.innotech.pageObject.objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.pageObject.objects.ObjectsUtils.checkVisibleElements;

public class MainPageInformationBlock {

    SelenideElement preparingForFlyHead = $("a[href*='#flight']");
    SelenideElement usefulInformationHead = $("a[href*='#useful']");
    SelenideElement aboutCompanyHead = $("a[href*='#company']");

    public MainPageInformationBlock checkHeadlines() {
        checkVisibleElements("информации", preparingForFlyHead, usefulInformationHead, aboutCompanyHead);
        return this;
    }
}
