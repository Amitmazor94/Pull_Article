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

public class TwelveMainArticlePage extends BasePage {

    public TwelveMainArticlePage(WebDriver driver) {
        super(driver);
    }

    public MongoClient mongoClient;
    public MongoDatabase db;



    public String readCategory() {

       if(!readText(category).equals("NULL")) return readText(category);
       else return "NULL";
    }


    public String readArticleTitle() {
        return readText(articleTitle);
    }

    public String readSubTitle() {
        return readText(subTitle);
    }

    public String readDate() {
        return extractDate(date);
    }

    public String readTime() {

        return extractTime(time);
    }



    public String readVideoUrl() {
        return getPictureUrl(picture);
    }

    public String readArticleBody() {
        return readListOfElements(articleBody);
    }
    public void clickN12Button(){
        waitForElement(siteLogo);
        clickButton(siteLogo);
    }

    public  void createDb(String site)
    {
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

    public Boolean dropTable(int i,String site)
    {
        MongoCollection<Document> collection= db.getCollection(site);
        if (collection.countDocuments()<i)
        {
            return true;
        }
        return false;
    }

    public void mongoInsertData(String category,String title, String subtitle,String summary,String image,String date_time,int count,String site)
    {
        MongoCollection<Document> collection= db.getCollection(site);
        InsertOneResult result = collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("author", site)
                .append("num", count)
                .append("category", category)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("summary",summary)
                .append("image",image)
                .append("date_time",date_time));
    }

    public void mongoUpdateData(String category,String title, String subtitle,String summary,String image,String date_time,int count,String site)
    {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("num", count)
                .append("author", site);


        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject().append("author", site)
                        .append("category", category)
                        .append("title", title)
                        .append("subtitle", subtitle)
                        .append("summary",summary)
                        .append("image",image)
                        .append("date_time",date_time));
        db.getCollection(site).updateOne(searchQuery, updateQuery);
    }

    public void pullN12Articles() throws InterruptedException {
        createDb("GQ-Dashboard");
        List<WebElement> articles= driver.findElements(mainArticles);
        System.out.println(articles.size());
        for(int i=0; i<articles.size(); i++) {
            Thread.sleep(4000);
            waitForElement(mainArticles);
            clickWebelementJs(mainArticles, i);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //articles.get(i).click();
            //twelveHomePage.clickMainArticle();
            System.out.println("category: " +readCategory() );
            System.out.println("Title: " + readArticleTitle());
            System.out.println("Sub Title: " + readSubTitle());
            System.out.println("Date: " + readDate());
            System.out.println("Time: " + readTime());
            System.out.println("Video: " + readVideoUrl());
            System.out.println("Content: " + readArticleBody());
          /* if (dropTable(articles.size(),"N12"))
            {
                mongoInsertData(readCategory(),readSubTitle(),readSubTitle(),readArticleBody(),readVideoUrl(),readDate()+" "+readTime(),i,"n12_news");
            }
            else {*/
                mongoUpdateData(readCategory(),readSubTitle(),readSubTitle(),readArticleBody(),readVideoUrl(),readDate()+" "+readTime(),i,"n12_news");
           //}
            driver.navigate().back();
            //clickN12Button();
           waitForElement(mainArticles);
            articles=driver.findElements(mainArticles);
           System.out.println(articles.size()+" i "+i);
        }
    }
}
