package com.epam.automation.test.hurt_me_plenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.automation.page.GoogleCloudPricingCalculatorPagePF;
import com.epam.automation.page.GoogleCloudStartPagePF;

import java.util.Locale;

public class WebDriverHMPGoogleCloudPFTest {

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
    public void successfulGoogleCloudEngineEstimation() {
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
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultList().getText().contains(vmClass.toLowerCase(Locale.ROOT)), "Wrong 'VM Class' value!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultList().getText().contains(machineType.split("\\s+")[0]), "Wrong 'Machine type' value!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultList().getText().contains(datacenterLocation.split("\\s+")[0]), "Wrong 'Datacenter location' value!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultList().getText().contains(localSsd.split("\\s+")[0]), "Wrong 'Local SSD' value!");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getResultList().getText().contains(committedUsage), "Wrong 'Committed usage' value");
        Assert.assertTrue(googleCloudPricingCalculatorPage.getTotalCost().getText().contains(totalCost), "Total Estimated Cost doesn't equals value got by manual testing!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserClosing() {
        driver.quit();
        driver = null;
    }
}