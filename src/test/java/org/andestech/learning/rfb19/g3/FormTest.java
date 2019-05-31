package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class FormTest
{
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    //@BeforeMethod // для мульти теста
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

    @Test(invocationCount = 3)
    public void multiTest() throws InterruptedException
    {
        wd = new ChromeDriver(chromeOptions);
        //    wd.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

        // wd.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

//        Wait<WebDriver> wait1 = new WebDriverWait(wd,5).withTimeout(Duration.ofSeconds(5)).
//                pollingEvery(Duration.ofSeconds(1));


        Wait<WebDriver> wait1 = new WebDriverWait(wd,2);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

        wd.get("http://google.com");

    }


   // @Test(invocationCount = 3, sequential = true, singleThreaded = true)
    @Test
    public void chromeTest() throws InterruptedException
    {
        wd = new ChromeDriver(chromeOptions);
       //    wd.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

       // wd.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

//        Wait<WebDriver> wait1 = new WebDriverWait(wd,5).withTimeout(Duration.ofSeconds(5)).
//                pollingEvery(Duration.ofSeconds(1));


        Wait<WebDriver> wait1 = new WebDriverWait(wd,2);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

        wd.get("http://andestech.org/learning/rfb18");

        WebElement webElement =
                wait1.until(x ->x.findElement(By.partialLinkText("New cust")));
        webElement.click();

        wd.findElement(By.id("name")).sendKeys("Пётр");
        wd.findElement(By.id("sname")).sendKeys("Петров");

        WebElement selectorElement =  wd.findElement(By.id("group_selector"));
        Select select = new Select(selectorElement);
        select.selectByValue("com");

        wd.findElement(By.id("login")).sendKeys("Peter");
        wd.findElement(By.id("pass")).sendKeys("P@ssw0rd");

        selectorElement.submit();

        Alert alert = wd.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();




        Thread.sleep(2000);


    }


//    @AfterMethod
    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
