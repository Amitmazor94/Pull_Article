package Excecutions;

import PageObjects.TwelveMainArticlePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ExecuteTwelve extends BaseExecute {




  @Test
  public void execute01_printMainArticleAttributes() {
  driver.get("https://www.n12.co.il/");
      twelveMainArticlePage.pullN12Articles();


  }

}
