package Excecutions;

import PageObjects.IsraelBasePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IsraelExecute extends BaseExecute {


    @Test
    public void execute02_pullIsraelArticles(){
        driver.get("https://www.israelhayom.co.il/news/law/article/14316609");
        System.out.println(israelMainArticlePage.extractTime());
        System.out.println(israelMainArticlePage.extractDate());
    }


}
