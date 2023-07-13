package PageObjects;

import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IsraelMainArticlePage extends IsraelBasePage {

    By category= By.cssSelector("ul[class='breadcrumbs widget element-EbQ257rVHv']");
    By title= By.cssSelector(".single-post-title .titleText");
    By subTitle= By.cssSelector("single-post-subtitle");
    By dateAndTime= By.cssSelector("span[class='single-post-meta-dates']");
    By articleBody= By.cssSelector("#text-content p");
    By images= By.cssSelector("#main .single-post-media_image");

    public IsraelMainArticlePage(WebDriver driver) {
        super(driver);
    }


    public String readCategory(){
        return readText(category);
    }
    public String title(){
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

}
