package com.epam.automation.window_handler;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WindowHandler {

    private final WebDriver driver;
    private ArrayList<String> tabs;

    public WindowHandler(WebDriver driver) {
        this.driver = driver;
    }

    public WindowHandler openNewTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        return this;
    }

    public WindowHandler holdTabs() {
        this.tabs = new ArrayList<>(this.driver.getWindowHandles());
        return this;
    }

    public WindowHandler switchTab(int index) {
        driver.switchTo().window(this.tabs.get(index));
        return this;
    }
}
