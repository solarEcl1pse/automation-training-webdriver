package com.epam.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.automation.wait.CustomConditions;

import java.time.Duration;

public class TenMineMailPagePF {

    private final static String HOMEPAGE_URL = "https://10minemail.com/en/";
    private final WebDriver driver;

    private String emailAddress;
    @FindBy(xpath = "//*[@id=\"mail\"]")
    private WebElement emailAddressField;
    @FindBy(xpath = "//span[contains(text(), 'Google Cloud Sales')]/..")
    private WebElement email;
    @FindBy(xpath = "//td[contains(text(),'USD')]")
    private WebElement totalCost;

    public TenMineMailPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TenMineMailPagePF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.pageLoadCompleted());
        return this;
    }

    public TenMineMailPagePF copyEmailAddress() {
        this.emailAddress = emailAddressField.getAttribute("value");
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public TenMineMailPagePF waitForEmail() {
        new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='inbox-area maillist']/div[4]"), "class", "inbox-dataList"));
        new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.elementToBeClickable(email));
        return this;
    }

    public TenMineMailPagePF openEmail() {
        email.click();
        return this;
    }

    public String getTotalCost() {
        return totalCost.getText();
    }
}
