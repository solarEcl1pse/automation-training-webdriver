package com.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.automation.wait.CustomConditions;

public class PastebinStartPagePF {

    private final static String HOMEPAGE_URL = "https://pastebin.com/";
    private final WebDriver driver;
    @FindBy(xpath = "//*[@id=\"postform-text\"]")
    private WebElement pasteCodeInput;
    @FindBy(xpath = "//*[@id=\"select2-postform-expiration-container\"]")
    private WebElement pasteExpirationDropdown;
    @FindBy(xpath = "//*[@id=\"postform-name\"]")
    private WebElement pasteNameInput;
    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;
    @FindBy(xpath = "//*[@id=\"select2-postform-format-container\"]")
    private WebElement pasteSyntaxHighlighting;

    public PastebinStartPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinStartPagePF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.pageLoadCompleted());
        return this;
    }

    public PastebinStartPagePF pasteCode(String value) {
        pasteCodeInput.click();
        pasteCodeInput.sendKeys(value);
        return this;
    }

    public PastebinStartPagePF choosePasteExpiration(String value) {
        pasteExpirationDropdown.click();
        driver.findElement(By.xpath("//li[text() = '" + value + "']")).click();
        return this;
    }

    public PastebinStartPagePF pasteName(String value) {
        pasteNameInput.click();
        pasteNameInput.sendKeys(value);
        return this;
    }

    public PastebinResultPagePF createNewPaste() {
        createNewPasteButton.click();
        return new PastebinResultPagePF(driver);
    }

    public PastebinStartPagePF chooseSyntaxHighlighting(String value) {
        pasteSyntaxHighlighting.click();
        driver.findElement(By.xpath("//li[text() = '" + value + "']")).click();
        return this;
    }
}
