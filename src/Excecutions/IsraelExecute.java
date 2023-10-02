package Excecutions;

import PageObjects.IsraelBasePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IsraelExecute extends BaseExecute {


    @Test
    public void execute02_pullIsraelArticles(){
        driver.get("https://www.israelhayom.co.il/");
        israelMainArticlePage.createDb("GQ-Dashboard");
        israelMainArticlePage.pullIsraelArticle();
        israelMainArticlePage.pullIsraelArticlesSec2();
        israelMainArticlePage.pollIsraelArticleSec3();

    }


}
