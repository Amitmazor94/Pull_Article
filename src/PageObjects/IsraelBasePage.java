package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IsraelBasePage {
    public WebDriver driver;
    public WebDriverWait wait = null;
    By article= By.cssSelector("#__next #main");
    By mainArticles= By.cssSelector(".posts-octet div[class='posts-main-gallery'] .row article");
    By mainArticles2=By.cssSelector("div[class='row post-card-list'] article[class='post post-card col col-initial-12 col-xs-6 col-med-3 '] .post-media figure img");

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

    public String getPictureUrl(By elementLocation) {
        String image="";
        WebElement industries = driver.findElement(article);
        List<WebElement> links = industries.findElements(elementLocation);
        System.out.println(links.size() + " Size");
        if (links.size() == 0) {
            image="Null";
            return image;
        }
        /*for (int i=0; i<links.size(); i++) {
            image+= links.get(i).getAttribute("src")+'\n';
        }*/
        else if(links.size()>0){
            image=links.get(0).getAttribute("src");
        }
        return image;
    }
    public String readListOfElements(By by) {
        String value = "";
        List<WebElement> elements;
        elements = driver.findElements(by);
        int elementsSize = elements.size();
        for (int i = 0; i < elementsSize; i++) {
            value += elements.get(i).getText() + '\n';
        }
        return value;}
}