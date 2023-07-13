package PageObjects;

import com.mongodb.client.MongoClient;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
//import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.result.*;
import org.bson.Document;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait = null;
    By article = By.cssSelector("div#article-wrap");
    By category = By.cssSelector("li[class=' here']");
    By articleTitle = By.cssSelector("section h1");
    By subTitle = By.cssSelector("section h2");
    By date = By.cssSelector(".display-date span");
    By time = By.cssSelector(".display-date span");
    By picture = By.cssSelector("figure img");
    By articleBody = By.cssSelector("section.article-body p");
    By siteLogo= By.cssSelector(".toolbar a.logo");
    By mainArticles=By.cssSelector("#part1 ul[box-title='רכיב ראשי חדשות'] li[data-image-type='i']");



    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(65));
    }




    public String readText(By elementLocation) {
        waitVisibility(elementLocation);

        String text="";
        if(driver.findElement(elementLocation).isDisplayed()){
            text=driver.findElement(elementLocation).getText();
        return text;}
        return "NULL";
    }

    public void clickButton(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

    public void writeText(By elementLocation, String value) {
        WebElement field = driver.findElement(elementLocation);
        field.clear();
        field.sendKeys(value);
    }

    public String readListOfElements(By by) {
        String value = "";
        List<WebElement> elements;
        elements = driver.findElements(by);
        int elementsSize = elements.size();
        for (int i = 0; i < elementsSize; i++) {
            value += elements.get(i).getText() + '\n';
        }
        return value;

    }

    public void waitVisibility(By by) {
        /*wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));*/
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));*/

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElement(By by){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String extractTime(By elementLocation) {
        waitVisibility(elementLocation);
        List<WebElement> elements = driver.findElements(elementLocation);
        WebElement element = elements.get(1);
        String time = element.getText();
        /* String modifiedTime= time.substring(18, 23);*/
        return
                time;

    }


    public String extractDate(By elementLocation) {
        waitVisibility(elementLocation);
        WebElement element = driver.findElement(elementLocation);
        String date = element.getText();
        String modifiedDate = date.substring(8, 16);
        return
                modifiedDate;
    }

    public String getPictureUrl(By elementLocation) {
       /* try {
            waitVisibility(elementLocation);
        } catch (TimeoutException e){return "null";}*/
        String image="";
        WebElement industries = driver.findElement(article);
        List<WebElement> links = industries.findElements(elementLocation);
        System.out.println(links.size() + " Size");
        if (links.size() == 0) {
            image="Null";
            return image;
        }
        for (int i=0; i<links.size(); i++) {
           image+= links.get(i).getAttribute("src")+'\n';
        }
        return image;
    }
    public List<WebElement> listOfArticles(By elementLocation) {
        List<WebElement> elements = driver.findElements(elementLocation);
        return elements;
    }

}






