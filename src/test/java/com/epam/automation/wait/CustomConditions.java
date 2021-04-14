package com.epam.automation.wait;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {

    public static ExpectedCondition<Boolean> pageLoadCompleted() {
        return webDriver -> (Boolean) ((JavascriptExecutor)
                webDriver).executeScript("return document.readyState").equals("complete");
    }
}