package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wait.CustomConditions;

public class PastebinResultPagePF {

    private final WebDriver driver;
    @FindBy(xpath = "//div[@class='source']")
    private WebElement resultCode;
    @FindBy(xpath = "//div[@class='expire']")
    private WebElement resultPasteExpiration;
    @FindBy(xpath = "//div[@class='info-top']")
    private WebElement resultName;
    @FindBy(xpath = "//div[@class='left']//a[@class='btn -small h_800']")
    private WebElement resultSyntaxHighlighting;

    public PastebinResultPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.pageLoadCompleted());
    }

    public String getResultCodeValue() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(resultCode));
        return resultCode.getText();
    }

    public String getResultPasteExpirationValue() {
        String result = null;
        if (resultPasteExpiration.getText().equals("10 MIN")) {
            result = "10 Minutes";
        }
        return result;
    }

    public String getResultNameValue() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(resultName));
        return resultName.getText();
    }

    public String getResultSyntaxHighlightingValue() {
        return resultSyntaxHighlighting.getText();
    }
}
