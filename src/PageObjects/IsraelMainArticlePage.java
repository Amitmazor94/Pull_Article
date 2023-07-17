package PageObjects;

import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IsraelMainArticlePage extends IsraelBasePage {

    By category= By.cssSelector("ul[class^=\"breadcrumbs widget\"]");
    By title= By.cssSelector(".single-post-title .titleText");
    By subTitle= By.cssSelector(".single-post-title span[class='titleText']");
    By dateAndTime= By.cssSelector("span[class='single-post-meta-dates']");
    By articleBody= By.cssSelector("#text-content p");
    By images= By.cssSelector(".single-post-media_image__img");
    By israelLogo= By.cssSelector(".logo .lazyload-wrapper  img");

    public IsraelMainArticlePage(WebDriver driver) {
        super(driver);
    }


    public String readCategory(){
        return readText(category);
    }
    public String readTitle(){
        return readText(title);
    }

    public String readSubTitle(){
        return readText(subTitle);
    }

    public String extractTime(){
        String stringedTime="";
        waitVisibility(dateAndTime);
       WebElement dAndT= driver.findElement(dateAndTime);
       String stringedDateAndTime= dAndT.getText();
       stringedTime= stringedDateAndTime.substring(11, 16);
       return stringedTime;
    }

    public String extractDate(){
        String stringedDate="";
        waitVisibility(dateAndTime);
        WebElement dAndT= driver.findElement(dateAndTime);
        String stringedDateAndTime= dAndT.getText();
        stringedDate= stringedDateAndTime.substring(0, 9);
        return stringedDate;
    }
    public String getImageUrl(){
        return getPictureUrl(images);
    }

    public String readArticleBody() {
        return readListOfElements(articleBody);
    }

    public void clickIsraelLogo(){
        waitForElement(israelLogo);
        clickButton(israelLogo);
    }

    public void pullIsraelArticle(){
        List<WebElement> articles= driver.findElements(mainArticles);
        System.out.println(articles.size());
        for(int i=0; i<articles.size(); i++) {
            waitForElement(mainArticles);
            articles.get(i).click();
            System.out.println("Category: "+readCategory());
            System.out.println("Title: "+readTitle());
            System.out.println("Subtitle: "+readSubTitle());
            System.out.println("Date: "+extractDate());
            System.out.println("Time: "+extractTime());
            System.out.println("Image: "+getImageUrl());
            System.out.println("Content: "+readArticleBody());
            clickIsraelLogo();
            waitForElement(mainArticles);
            articles=driver.findElements(mainArticles);
            System.out.println(articles.size()+" i "+i);
    }

}
    public void pullIsraelArticlesSec2() {
        List<WebElement> articles = driver.findElements(mainArticles2);
        System.out.println(articles.size());
        for (int i = 0; i < articles.size(); i++) {
            waitForElement(mainArticles2);
            articles.get(i).click();
            System.out.println("Category: " + readCategory());
            System.out.println("Title: " + readTitle());
            System.out.println("Subtitle: " + readSubTitle());
            System.out.println("Date: " + extractDate());
            System.out.println("Time: " + extractTime());
            System.out.println("Image: " + getImageUrl());
            System.out.println("Content: " + readArticleBody());
            clickIsraelLogo();
            waitForElement(mainArticles2);
            articles = driver.findElements(mainArticles2);
            System.out.println(articles.size() + " i " + i);
        }
    }
}
