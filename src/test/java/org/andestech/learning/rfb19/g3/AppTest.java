package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AppTest
{
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "E:\\selenium_drivers\\chromedriver_2.46.628402.exe");

//        System.setProperty("webdriver.chrome.driver",
//                "E:\\selenium_drivers\\chromedriver_74.0.3729.6.exe");
    // chromedriver_2.46.628402.exe
    System.out.println("+++ Class: " + this);

    chromeOptions = new ChromeOptions();

    //chromeOptions.addArguments("--user-data-dir=C:\\Users\\and\\AppData\\Local\\Google\\Chrome\\User Data");

     chromeOptions.setBinary("E:\\progs\\chrome-win\\chrome.exe");
     chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
     //chromeOptions.setHeadless(true);


    }

    @Test
    public void chromeTest() throws InterruptedException
    {
        wd = new ChromeDriver(chromeOptions);
       //    wd.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

       // wd.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

//        Wait<WebDriver> wait1 = new WebDriverWait(wd,5).withTimeout(Duration.ofSeconds(5)).
//                pollingEvery(Duration.ofSeconds(1));


        Wait<WebDriver> wait1 = new WebDriverWait(wd,5);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

        wd.get("http://yandex.ru");

        WebElement webElement = wd.findElement(By.name("text"));

        String searchPrase = "Вояджер";
        String ratingAddress = "nasa";

        webElement.sendKeys(searchPrase);
        webElement.submit();

        HashMap<Integer,String> rating =  new HashMap<>();

        String resultDataSelector = "ul > li.serp-item h2>a";

        int i = 0;
        List<WebElement> datas = wd.findElements(By.cssSelector(resultDataSelector));
        for(WebElement webElement1: datas)
        {
            String link =  webElement1.getAttribute("href");
            System.out.println(i++ + " :" + link);
            if(link.toLowerCase().indexOf(ratingAddress) != -1) rating.put(i,link);

        }

        // ul > li.serp-item h2>a'

        int N = 6;

        for(int k = 0; k<N; k++)
        {

            WebElement pnnext = wd.findElement(By.linkText("дальше"));
            pnnext.click();

            datas.clear();
            datas = wd.findElements(By.cssSelector(resultDataSelector));
            for(WebElement webElement1 :  datas)
            {
                String link =  webElement1.getAttribute("href");
                System.out.println(i++ + " :" + link);
                if(link.toLowerCase().indexOf(ratingAddress) != -1) rating.put(i,link);
            }

        }

        System.out.println("---------------------rating-----------------------------------");
        System.out.println("Search prase: " + searchPrase + ", rating for web with text:" + ratingAddress);
        for(int key: rating.keySet())
        {
            System.out.println(key + " : " + rating.get(key));
        }

    }

    @Test
    public void siteRaitingTest() throws InterruptedException
    {
        wd = new ChromeDriver(chromeOptions);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        wd.get("http://google.com");

        WebElement we = wait2.until( x -> x.findElement(By.name("q")));


        we.sendKeys("кредит в банке");
        we.submit();


        List<WebElement> datas = wd.findElements(By.cssSelector("div.g div.r > a"));

        int i = 0;

        for(WebElement element :  datas)
        {
            System.out.println(i++ + " : " + element.getAttribute("href"));
        }


        int N = 9;

        for(int k = 0; k<N; k++)
        {

            WebElement pnnext = wd.findElement(By.id("pnnext"));
            pnnext.click();

            datas.clear();
            datas = wd.findElements(By.cssSelector("div.g div.r > a"));
            for(WebElement element :  datas)
            {
                System.out.println(i++ + " : " + element.getAttribute("href"));
            }


        }

    }



    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
