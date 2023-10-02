package Excecutions;

import PageObjects.IsraelHomePage;
import PageObjects.IsraelMainArticlePage;
import PageObjects.TwelveHomePage;
import PageObjects.TwelveMainArticlePage;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.BeforeClass;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseExecute {
    public static WebDriver driver=null;
    static TwelveHomePage twelveHomePage;
    static TwelveMainArticlePage twelveMainArticlePage;
    static IsraelMainArticlePage israelMainArticlePage;



@BeforeClass
    public static void browserSetup(){
    System.setProperty("webdriver.chrome.driver", "C:\\הורדות קורס\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addExtensions(new File("./Extensions/extension_1_50_0_0.crx"));
    //options.addArguments("--incognito");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
     Actions actions=new Actions(driver);
    twelveHomePage= new TwelveHomePage(driver);
    twelveMainArticlePage= new TwelveMainArticlePage(driver);
    israelMainArticlePage=new IsraelMainArticlePage(driver);

    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

}


@AfterClass
    public static void closeSession(){
   driver.close();
   driver.quit();
}



}
