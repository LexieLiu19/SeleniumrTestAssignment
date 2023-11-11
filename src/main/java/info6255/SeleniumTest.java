package info6255;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTest {

    public static void main(String[] args) {

        // initial set up:
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        // login:
        //Please set the username and password as env variables
        String userName = System.getenv("username");
        String password = System.getenv("password");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        try {
            driver.get("https://me.northeastern.edu");
            //wait and enter username
            WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            userNameInput.sendKeys(userName);
            //click
            driver.findElement(By.id("idSIButton9")).click();

            //wait and enter password
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.sendKeys(password);
            driver.findElement(By.id("idSIButton9")).click();

            //confirm duo
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button"))).click();
            //stay signed in
            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();

            // click "Resources"Link:
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Resources"))).click();
            // click "Academics, Classes & Registration"
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("categoryItem_5ebd5061"))).click();
            //click "My Transcript" link
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Transcript"))).click();
            //todo: Xu: transcript page login...

        } finally {
//            driver.quit();
            System.out.println("end");
        }
    }
}
