package info6255;


import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;


public class Testno4 {


    public static void main(String[] args) {
        // initial set up:
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions action = new Actions(driver);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increase the wait time


        try {
            // Maximize the browser window
            driver.manage().window().maximize();


            // Navigate to the desired URL
            driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
            //xpath for digital repository services on SOS
            WebElement menuItem=driver.findElement(By.xpath("//span[contains(text(),'digital repository service')]"));
            action.moveToElement(menuItem);
            wait.until(ExpectedConditions.visibilityOf(menuItem));
            action.click();
            action.perform();
            Thread.sleep(4000);
            //Checking the currentUrl of the page
            System.out.println(driver.getCurrentUrl());

            // We are getting single current handle
            String currentHandle= driver.getWindowHandle();
            //We have taken multiple handles and set it to a string set
            Set<String> handles=driver.getWindowHandles();
            // handles is an set oof windows which iterates and stores temp value in actual(string)
            for(String actual: handles) { //for(int i=0;i<handles.length();i++){}

                //if actual handle isn't a current handle it will switch the driver to current handle/window
                if (!actual.equalsIgnoreCase(currentHandle)) {
                    //switching to the opened tab
                    driver.switchTo().window(actual);
                    String drsTitle=driver.getTitle();
                    //checking title of second window
                    System.out.println(drsTitle);
                    //xpath for dataset button on drs
                    //here we are setting a xpath to a variable datasetButton of type webelement to perform further actions
                    WebElement datasetButton=driver.findElement(By.xpath("//a[@class='btn btn-clear btn-block' and @href='/datasets']"));
                    action.moveToElement(datasetButton);
                    wait.until(ExpectedConditions.visibilityOf(datasetButton));
                    action.click();
                    action.perform();
                    Thread.sleep(4000);


                }
            }

            //xpath for search field with place holder
            driver.findElement(By.xpath("//input[@id='searchFieldHeader' and @placeholder='Search this featured content']")).sendKeys("csv");
            driver.findElement(By.xpath("(//*[@id='search-submit-header'])[2]")).click();
            Thread.sleep(10000);




            //click on search result 1st element


            WebElement serachItem=driver.findElement(By.xpath("//a[contains(@href, '/files/neu:m0472768f')]"));
            action.moveToElement(serachItem);
            action.click();
            action.perform();
            Thread.sleep(4000);
            //------------------download file-------------------


            WebElement dowload=driver.findElement(By.xpath("//a[contains(text(),'Zip File')]"));
            action.moveToElement(dowload);
            action.click();
            action.perform();
            Thread.sleep(4000);




        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
            System.out.println("Test completed successfully.");
        }
    }
}

