package info6255;

import info6255.utils.ScreenshotUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScenarioOne {


    public static void testScenarioOne(WebDriver driver) {

        // login:
        //  Set the username and password as env variables for Security:
        String userName = System.getenv("username");
        String userId = System.getenv("userId");
        String password = System.getenv("password");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        String scenarioOneScreenshotPath = "screenshots/One/";

        try {
            driver.get("https://me.northeastern.edu");
            //wait and enter username
            WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            userNameInput.sendKeys(userName);

            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-1.png");
            //click
            driver.findElement(By.id("idSIButton9")).click();

            //wait and enter password
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.sendKeys(password);

            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-2.png");
            driver.findElement(By.id("idSIButton9")).click();

            //confirm duo

            WebElement duoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button")));

            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-3.png");
            duoElement.click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-4.png");
            //stay signed in

            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();


            // click "Resources"Link:

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Resources"))).click();

            // click "Academics, Classes & Registration"

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("categoryItem_5ebd5061"))).click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-5.png");
            //click "My Transcript" link
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Transcript"))).click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-6.png");
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
            }


            //Log in again:
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-7.png");
            WebElement transcriptUserName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement transcriptPassword = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement logInBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("form-button")));

            transcriptUserName.sendKeys(userId);
            transcriptPassword.sendKeys(password);
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-8.png");

            logInBtn.click();


            //Duo:
            driver.switchTo().frame("duo_iframe"); // duo iframe
            WebElement pushButton;

            try {
                // get element:
                pushButton = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'auth-button') and contains(@class, 'positive')][@type='submit']"))));
                pushButton.click();
            } catch (StaleElementReferenceException e) {
                // if any exception, re-get the element
                pushButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(@class, 'auth-button') and contains(@class, 'positive')][@type='submit']")));
                pushButton.click();
            }

            driver.switchTo().defaultContent();

            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-9.png");

            //level select:
            Select levelSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("levl_id"))));
            levelSelect.selectByValue("GR");

            Select typeSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("type_id"))));
            typeSelect.selectByValue("AUDI");
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-10.png");

            WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='submit'][value='Submit']")));
            submitButton.click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-11.png");


            ((JavascriptExecutor) driver).executeScript("window.print();");


        } finally {
            System.out.println(" Scenario One Ends");
        }
    }
}
