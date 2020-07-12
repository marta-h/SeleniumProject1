package selenium.automated.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class DevToTests {


        WebDriver driver;
        String url = "https://dev.to/";

        public void HighlightElement(WebElement element){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);




        }


        @Before
        public void SetUp(){
            System.setProperty("webdriver.chrome.driver","C:\\Testowanie\\Selenium Drivers\\chromedriver.exe");

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(url);


        }

        @Test
        public void OpenDevTo(){
            String currentUrl = driver.getCurrentUrl();
            assertTrue("The current url isn't dev.to", url.equals(currentUrl));

        }

        @Test
        public void SearchField(){
            WebElement searchField = driver.findElement(By.id("nav-search"));
            searchField.sendKeys("Tester");
            searchField.submit();


            String searchTitle = driver.getTitle();
            assertTrue("Wrong title", searchTitle.equals("DEV Community \uD83D\uDC69\u200D\uD83D\uDCBB\uD83D\uDC68\u200D\uD83D\uDCBB"));

        }

        @Test
        public void LatestTab(){
            WebElement latestTab = driver.findElement(By.xpath("//*[@id=\"on-page-nav-controls\"]/div/nav/a[6]"));
            latestTab.click();

            String latestTitle = driver.getTitle();
            assertTrue("Wrong tab title", latestTitle.equals("DEV Community \uD83D\uDC69\u200D\uD83D\uDCBB\uD83D\uDC68\u200D\uD83D\uDCBB"));


        }

        @Test
        public void MoreButton(){
            WebElement moreButton = driver.findElement(By.id("main-nav-more-trigger"));
            moreButton.click();

            WebElement faq = driver.findElement(By.xpath("//*[@id=\"main-nav-more\"]/a[2]"));
            assertTrue("List doesn't expand",faq.isDisplayed());



        }

        @Test
        public void GoToWeekAndOpenFirstPost(){
            WebElement week = driver.findElement(By.cssSelector("#on-page-nav-controls > div > nav > a:nth-child(2)"));
            HighlightElement(week);
            week.click();


            WebDriverWait wait = new WebDriverWait(driver, 7);
            wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));


            WebElement firstPostOnWeek = driver.findElement(By.className("crayons-story__body"));
            HighlightElement(firstPostOnWeek);

            WebElement firstPostTitle = driver.findElement(By.cssSelector(".crayons-story__title > a"));
            HighlightElement(firstPostTitle);
            String linkToFirstPost = firstPostTitle.getAttribute("href");
            firstPostOnWeek.click();


            String currentUrl = driver.getCurrentUrl();
            wait.until(ExpectedConditions.urlToBe(currentUrl));
            assertEquals("url isn't the same as link(href) value", linkToFirstPost, currentUrl);

//            WebElement title1 = driver.findElement(By.xpath("//*[@id=\"main-title\"]/div[2]/h1"));
//            WebElement title2 = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/main/div/article/header/div[2]/h1"));
//            assertTrue(title1.equals(title2));





        }


}
