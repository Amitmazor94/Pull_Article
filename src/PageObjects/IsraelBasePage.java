package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IsraelBasePage {
    public WebDriver driver;
    public WebDriverWait wait = null;

    public IsraelBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(65));
    }

    public void waitVisibility(By by) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String readText(By elementLocation) {
        waitVisibility(elementLocation);
        String text = "";
        if (driver.findElement(elementLocation).isDisplayed()) {
            text = driver.findElement(elementLocation).getText();
            return text;
        }
        return "NULL";
    }


    public void waitForElement(By by){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void clickButton(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

}
