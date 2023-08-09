package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TwelveHomePage extends BasePage {
    //TwelveMainArticlePage twelveMainArticlePage;
    By mainArticle = By.cssSelector(".theme-black a[data-type=title]");

    public TwelveHomePage(WebDriver driver) {
        super(driver);
    }


    public TwelveHomePage clickMainArticle() throws InterruptedException {
        try{
        clickButton(mainArticle);}catch (TimeoutException e){
            clickButton(mainArticle);
        }
        return this;
    }

    public List<WebElement> n12HomepageArticles(){
        return
        listOfArticles(mainArticles);
    }



    }

