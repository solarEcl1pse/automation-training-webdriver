package bring_it_on.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinResultPagePF;
import page.PastebinStartPagePF;

public class WebDriverBIOPastebinPFTest {

    private final String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private final String syntaxHighlighting = "Bash";
    private final String pasteExpiration = "10 Minutes";
    private final String pasteName = "how to gain dominance among developers";
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
                .chooseSyntaxHighlighting(syntaxHighlighting)
                .choosePasteExpiration(pasteExpiration)
                .pasteName(pasteName)
                .createNewPaste();
        Assert.assertEquals(pastebinResultPagePF.getResultNameValue(), pasteName, "Wrong paste name value!");
        Assert.assertEquals(pastebinResultPagePF.getResultSyntaxHighlightingValue(), syntaxHighlighting, "Wrong syntax highlighting value!");
        Assert.assertEquals(pastebinResultPagePF.getResultCodeValue(), code, "Wrong code value!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserClosing() {
        driver.quit();
        driver = null;
    }
}
