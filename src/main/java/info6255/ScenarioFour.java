package info6255;

import info6255.utils.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class ScenarioFour {

    public static void testScenarioFour(WebDriver driver) {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String screenShotsSavePath = "screenshots/Four/";

        try {
            driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
            ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s4-step-1.png");

            WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'digital repository service')]")));
            action.moveToElement(menuItem).click().perform();


            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();


            for (String actual : handles) {
                if (!actual.equalsIgnoreCase(currentHandle)) {
                    driver.switchTo().window(actual);
                    ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s4-step-2.png");
                    WebElement datasetButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@class='btn btn-clear btn-block' and @href='/datasets']")));
                    action.moveToElement(datasetButton).click().perform();

                    WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@id='searchFieldHeader' and @placeholder='Search this featured content']")));
                    searchField.sendKeys("csv");
                    ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s4-step-3.png");

                    wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//*[@id='search-submit-header'])[2]"))).click();

                    WebElement searchItem = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href, '/files/neu:m0472768f')]")));
                    action.moveToElement(searchItem).click().perform();
                    ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s4-step-4.png");
                    WebElement download = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(text(),'Zip File')]")));
                    action.moveToElement(download).click().perform();
                }
            }
        } finally {
            System.out.println("Scenario Four Ends");
        }
    }
}
