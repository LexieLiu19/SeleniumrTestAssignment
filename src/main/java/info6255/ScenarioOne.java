package info6255;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScenarioOne {

    public static void test() {
        System.out.println("success");
    }

    public static void testScenarioOne(WebDriver driver) {

        // login:
        //  Set the username and password as env variables for Security:
        String userName = System.getenv("username");
        String userId = System.getenv("userId");
        String password = System.getenv("password");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(2000));

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

            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
            }


            //Log in again:
            WebElement transcriptUserName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement transcriptPassword = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement logInBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("form-button")));

            transcriptUserName.sendKeys(userId);
            transcriptPassword.sendKeys(password);
            logInBtn.click();

            //Duo:
            driver.switchTo().frame("duo_iframe"); // 替换为实际的iframe名称或ID
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


            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();


            //level select:
            Select levelSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("levl_id"))));
            System.out.println(levelSelect);
            levelSelect.selectByValue("GR");


//            WebElement levelSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("levl_id")));
//            System.out.println(levelSelect);
//            Select select_level = new Select(levelSelect);
////            select_level.selectByValue("GR");
//
//            WebElement typeSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("type_id")));
//            System.out.println(typeSelect);
//            Select select_type = new Select(typeSelect);
//            System.out.println(select_type);
//            select_type.selectByValue("AUDI");
//            WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='submit'][value='Submit']")));
//            submitButton.click();

        } finally {
//            driver.quit();
            System.out.println("end");
        }
    }
}
