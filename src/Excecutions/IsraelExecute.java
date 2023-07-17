package Excecutions;

import PageObjects.IsraelBasePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IsraelExecute extends BaseExecute {


    @Test
    public void execute02_pullIsraelArticles(){
        driver.get("https://www.israelhayom.co.il/");
        israelMainArticlePage.pullIsraelArticle();
        israelMainArticlePage.pullIsraelArticlesSec2();
       /* System.out.println(israelMainArticlePage.readCategory());
        System.out.println(israelMainArticlePage.readTitle());
        System.out.println(israelMainArticlePage.readSubTitle());
        System.out.println(israelMainArticlePage.extractDate());
        System.out.println(israelMainArticlePage.extractTime());
        System.out.println(israelMainArticlePage.getImageUrl());
        System.out.println(israelMainArticlePage.readArticleBody());*/
    }


}
