package hardcore.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.GoogleCloudPricingCalculatorPagePF;
import page.GoogleCloudStartPagePF;
import page.TenMineMailPagePF;
import window_handler.WindowHandler;

public class WebDriverHardcoreGoogleCloudPFTest {

    private final String searchTerm = "Google Cloud Platform Pricing Calculator";
    private final String section = "Compute Engine";
    private final String numberOfInstances = "4";
    private final String operatingSystemSoftware = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
    private final String vmClass = "Regular";
    private final String series = "N1";
    private final String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private final String numberOfGpus = "1";
    private final String gpuType = "NVIDIA Tesla V100";
    private final String localSsd = "2x375 GB";
    private final String datacenterLocation = "Frankfurt (europe-west3)";
    private final String committedUsage = "1 Year";
    private final String totalCost = "USD 1,082.77";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void successfulGoogleCloudEngineEmailEstimation() {
        GoogleCloudPricingCalculatorPagePF googleCloudPricingCalculatorPage = new GoogleCloudStartPagePF(driver)
                .openPage()
                .pasteSearchTerm(searchTerm)
                .startSearch()
                .openLink(searchTerm)
                .selectSection(section)
                .pasteNumberOfInstances(numberOfInstances)
                .chooseOperatingSystemSoftware(operatingSystemSoftware)
                .chooseVMClass(vmClass)
                .chooseSeries(series)
                .chooseMachineType(machineType)
                .selectAddGpusCheckbox()
                .chooseNumberOfGpus(numberOfGpus)
                .chooseGpuType(gpuType)
                .chooseLocalSsd(localSsd)
                .chooseDatacenterLocation(datacenterLocation)
                .chooseCommittedUsage(committedUsage)
                .selectAddToEstimateButton();
        WindowHandler windowHandler = new WindowHandler(driver)
                .openNewTab(driver)
                .holdTabs()
                .switchTab(1);
        TenMineMailPagePF tenMineMailPagePF = new TenMineMailPagePF(driver)
                .openPage()
                .copyEmailAddress();
        windowHandler.switchTab(0);
        googleCloudPricingCalculatorPage
                .switchToFrame()
                .selectEmailEstimateButton()
                .pasteEmail(tenMineMailPagePF.getEmailAddress())
                .selectSendEmailButton();
        windowHandler.switchTab(1);
        tenMineMailPagePF
                .waitForEmail()
                .openEmail();
        Assert.assertTrue(tenMineMailPagePF.getTotalCost().contains(totalCost), "Total Estimated Cost doesn't equals value got by manual testing!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserClosing() {
        driver.quit();
        driver = null;
    }
}