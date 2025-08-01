package newsbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class NewsCrawler {

    public static List<NewsItem> crawlNews() {
        WebDriver driver = null;
        List<NewsItem> newsList = new ArrayList<>();

        try {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("detach", true);
            options.addArguments("--log-level=3");  // 경고 제거

            driver = new ChromeDriver(options);
            driver.get("https://biz.chosun.com/it-science/");
            Thread.sleep(3000);  // 렌더링 대기

            List<WebElement> articleBlocks = driver.findElements(By.cssSelector("div.story-card__headline-container"));

            for (WebElement block : articleBlocks) {
                WebElement linkTag = block.findElement(By.tagName("a"));
                WebElement spanTag = linkTag.findElement(By.tagName("span"));

                String title = spanTag.getText();
                String link = linkTag.getAttribute("href");

                newsList.add(new NewsItem(title, link));
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 newsList 비어있음
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        return newsList;
    }
}
