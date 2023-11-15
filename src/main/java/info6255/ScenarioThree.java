package info6255;


import info6255.utils.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;


public class ScenarioThree {

    public static void testScenarioThree(WebDriver driver) {
        String currentDir = System.getProperty("user.dir");
        String downloadFilePath = Paths.get(currentDir, "downloadPDF").toString();
        String screenShotsSavePath = "screenshots/Three/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://service.northeastern.edu/tech?id=classrooms");
            WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ng-binding[href*='classroom_details']")));
            ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s3-step1.png");
            linkElement.click();
            WebElement pdfLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ng-binding[href*='Hybrid_Nuflex_Classroom.pdf']")));
            ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s3-step2.png");
            pdfLinkElement.click();


            ScreenshotUtil.takeScreenshot(driver, screenShotsSavePath + "s3-step3.png");
            String pdfUrl = driver.getCurrentUrl();
            driver.get(pdfUrl);
        } finally {
         
            System.out.println("Scenario Three Ends");
        }
    }


}

