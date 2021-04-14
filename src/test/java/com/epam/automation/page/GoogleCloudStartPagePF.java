package com.epam.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.automation.wait.CustomConditions;

public class GoogleCloudStartPagePF {

    private final static String HOMEPAGE_URL = "https://cloud.google.com/";
    private final WebDriver driver;
    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public GoogleCloudStartPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudStartPagePF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.pageLoadCompleted());
        return this;
    }

    public GoogleCloudStartPagePF pasteSearchTerm(String value) {
        searchInput.click();
        searchInput.sendKeys(value);
        return this;
    }

    public GoogleCloudSearchResultPagePF startSearch() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        return new GoogleCloudSearchResultPagePF(driver);
    }
}
