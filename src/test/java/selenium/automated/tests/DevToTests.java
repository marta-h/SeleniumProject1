package selenium.automated.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;



public class DevToTests {


        WebDriver driver;
        String url = "https://dev.to/";


        @Before
        public void SetUp(){
            System.setProperty("webdriver.chrome.driver","C:\\Testowanie\\Selenium Drivers\\chromedriver.exe");
            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(url);


        }

        @Test
        public void FirstTest(){
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



}
