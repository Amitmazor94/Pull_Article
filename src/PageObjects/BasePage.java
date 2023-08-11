package PageObjects;

import com.mongodb.client.MongoClient;
import org.bson.types.ObjectId;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    By category = By.cssSelector("nav[class='breadcrumbs-v_2017 not_for_print']");
    By articleTitle = By.cssSelector("section h1");
    By subTitle = By.cssSelector("section h2");
    By date = By.cssSelector(".display-date span");
    By time = By.cssSelector(".display-date span");
    By picture = By.cssSelector("figure img");
    By articleBody = By.cssSelector("section[class='article-body'] p");
    By siteLogo= By.cssSelector(".toolbar .logo");
    By mainArticles=By.xpath("//*[@id=\"part1\"]/ul/li/figure");
    Actions actions;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(65));
    }




    public String readText(By elementLocation) {
        waitVisibility(elementLocation);

        String text="";
        if (waitVisibility(elementLocation)==true){

        if(driver.findElement(elementLocation).isDisplayed())
        {
            text=driver.findElement(elementLocation).getText();
            return text;}
        }
            text="NULL";
        return text;
    }

    public void clickButton(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }
    public void clickJS(By elementLocation) {
        waitVisibility(elementLocation);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",elementLocation);
        //driver.findElement(elementLocation).click();
    }

    public void clickWebelementJs(By elementLocation, int i) throws InterruptedException {
        waitVisibility(elementLocation);
        List <WebElement> elements=driver.findElements(elementLocation);
        actions=new Actions(driver);
        /*JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",elements.get(i));*/
        actions.moveToElement(elements.get(i)).click().perform();
        //Thread.sleep(2000);
        //element.click();
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

    public boolean waitVisibility(By by) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try{wait.until(ExpectedConditions.visibilityOfElementLocated(by));}catch(TimeoutException e){return false;}
        return true;
    }

    public void waitForElement(By by){
        try {
            Thread.sleep(2000);
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






