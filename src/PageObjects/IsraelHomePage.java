package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsraelHomePage extends IsraelBasePage {

    public IsraelHomePage(WebDriver driver) {
        super(driver);
    }
    By mainArticles= By.cssSelector(".posts-main-gallery .row article");
    By israelLogo= By.cssSelector(".logo .lazyload-wrapper  img");



}
