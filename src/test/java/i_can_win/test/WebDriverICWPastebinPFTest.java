package i_can_win.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinResultPagePF;
import page.PastebinStartPagePF;

public class WebDriverICWPastebinPFTest {

    private final String code = "Hello from WebDriver";
    private final String pasteExpiration = "10 Minutes";
    private final String pasteName = "helloweb";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void successfulNewPasteCreation() {
        PastebinResultPagePF pastebinResultPagePF = new PastebinStartPagePF(driver)
                .openPage()
                .pasteCode(code)
                .choosePasteExpiration(pasteExpiration)
                .pasteName(pasteName)
                .createNewPaste();
        Assert.assertEquals(pastebinResultPagePF.getResultCodeValue(), code, "Wrong code value!");
        Assert.assertEquals(pastebinResultPagePF.getResultPasteExpirationValue(), pasteExpiration, "Wrong paste expiration value!");
        Assert.assertEquals(pastebinResultPagePF.getResultNameValue(), pasteName, "Wrong paste name value!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserClosing() {
        driver.quit();
        driver = null;
    }
}
