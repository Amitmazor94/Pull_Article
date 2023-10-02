package PageObjects;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class IsraelMainArticlePage extends IsraelBasePage {
    int size = 0;
    int lengthOfArticles;
    public MongoClient mongoClient;
    public MongoDatabase db;
    By category = By.cssSelector("ul[class^=\"breadcrumbs widget\"]");
    By title = By.cssSelector(".single-post-title .titleText");
    By subTitle = By.cssSelector(".single-post-subtitle");
    By dateAndTime = By.cssSelector("span[class='single-post-meta-dates']");
    By articleBody = By.cssSelector("#text-content p");
    By images = By.cssSelector(".single-post-media_image__img");
    By israelLogo = By.cssSelector(".logo .lazyload-wrapper  img");
    By moreNewsButton = By.linkText("https://www.israelhayom.co.il/news");

    public IsraelMainArticlePage(WebDriver driver) {
        super(driver);
    }


    public String readCategory() {
        return readText(category);
    }

    public String readTitle() {
        return readText(title);
    }

    public String readSubTitle() {
        return readText(subTitle);
    }

    public String extractTime() {
        String stringedTime = "";
        waitVisibility(dateAndTime);
        WebElement dAndT = driver.findElement(dateAndTime);
        String stringedDateAndTime = dAndT.getText();
        stringedTime = stringedDateAndTime.substring(11, 15);
        return stringedTime;
    }

    public String extractDate() {
        String stringedDate = "";
        waitVisibility(dateAndTime);
        WebElement dAndT = driver.findElement(dateAndTime);
        String stringedDateAndTime = dAndT.getText();
        stringedDate = stringedDateAndTime.substring(0, 8);
        return stringedDate;
    }

    public String getImageUrl() {
        return getPictureUrl(images);
    }

    public String readArticleBody() {
        return readListOfElements(articleBody);
    }

    public void clickIsraelLogo() {
        waitForElement(israelLogo);
        clickButton(israelLogo);
    }


    public void createDb(String site) {
        //"mongodb+srv://tzafriravram:NJJXeCYygkVrLxHl@cluster0.w9dqbue.mongodb.net/";
//"mongodb+srv://yaal-2122:wsmJQ3ggbFxFtHX@cluster0.qnlfmxm.mongodb.net/GQ-Dashboard?retryWrites=true&w=majority";
        String connectionString = "mongodb+srv://yaal-2122:wsmJQ3ggbFxFtHX@cluster0.qnlfmxm.mongodb.net/GQ-Dashboard?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        //  mongoClient = new MongoClient(settings);//"localhost" , 27017 );
        System.out.println("Created Mongo Connection successfully");

        MongoClient mongoClient = MongoClients.create(connectionString);
        db = mongoClient.getDatabase(site);

        System.out.println("Get database is successful");
    }

    public Boolean dropTable(int i, String site) {
        MongoCollection<Document> collection = db.getCollection(site);
        if (collection.countDocuments() < i) {
            return true;
        }
        return false;
    }

    public void mongoInsertData(String category, String title, String subtitle, String summary, String image, String date_time, int count, String site) {
        MongoCollection<Document> collection = db.getCollection(site);
        InsertOneResult result = collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("author", site)
                .append("num", count)
                .append("category", category)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("summary", summary)
                .append("image", image)
                .append("date_time", date_time));
    }

    public void mongoUpdateData(String category, String title, String subtitle, String summary, String image, String date_time, int count, String site) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("num", count)
                .append("author", site);


        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject().append("author", site)
                        .append("category", category)
                        .append("title", title)
                        .append("subtitle", subtitle)
                        .append("summary", summary)
                        .append("image", image)
                        .append("date_time", date_time));
        db.getCollection(site).updateOne(searchQuery, updateQuery);
    }


    public void pullIsraelArticle() {
        //createDb("GQ-Dashboard");
        List<WebElement> articles = driver.findElements(mainArticles);
        size = articles.size();
        System.out.println(articles.size());
        for (int i = 0; i < articles.size(); i++) {
            waitForElement(mainArticles);
            articles.get(i).click();
            System.out.println("Category: " + readCategory());
            System.out.println("Title: " + readTitle());
            System.out.println("Subtitle: " + readSubTitle());
            System.out.println("Date: " + extractDate());
            System.out.println("Time: " + extractTime());
            System.out.println("Image: " + getImageUrl());
            System.out.println("Content: " + readArticleBody());
            if (dropTable(articles.size(), "israel_hayom_news")) {
                mongoInsertData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i, "israel_hayom_news");
            } else {
                mongoUpdateData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i, "israel_hayom_news");
            }
            clickIsraelLogo();
            waitForElement(mainArticles);
            articles = driver.findElements(mainArticles);
            System.out.println(articles.size() + " i " + i);
        }

    }

    public void pullIsraelArticlesSec2() {
        //createDb("GQ-Dashboard");
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
            if (dropTable(articles.size() + size, "israel_hayom_news")) {
                mongoInsertData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i + size, "israel_hayom_news");
            } else {
                mongoUpdateData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i + size, "israel_hayom_news");
            }
            clickIsraelLogo();
            waitForElement(mainArticles2);
            articles = driver.findElements(mainArticles2);
            System.out.println(articles.size() + " i " + i);
        }
        size += articles.size();
    }

    public void pollIsraelArticleSec3() {
        driver.get("https://www.israelhayom.co.il/news");
        List<WebElement> articles = driver.findElements(mainArticles3);
        for (int i = 0; i < articles.size(); i++) {
            waitForElement(mainArticles3);
            articles.get(i).click();
            System.out.println("Category: " + readCategory());
            System.out.println("Title: " + readTitle());
            System.out.println("Subtitle: " + readSubTitle());
            System.out.println("Date: " + extractDate());
            System.out.println("Time: " + extractTime());
            System.out.println("Image: " + getImageUrl());
            System.out.println("Content: " + readArticleBody());
            if (dropTable(articles.size() + size, "israel_hayom_news")) {
                mongoInsertData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i + size, "israel_hayom_news");
            } else {
                mongoUpdateData(readCategory(), readTitle(), readSubTitle(), readArticleBody(), getImageUrl(), extractDate() + " " + extractTime(), i + size, "israel_hayom_news");
            }
            driver.navigate().back();
            waitForElement(mainArticles3);
            articles = driver.findElements(mainArticles3);
            System.out.println(articles.size() + " i " + i);
        }


    }
}
