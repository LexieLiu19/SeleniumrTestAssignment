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
import java.util.HashMap;

public class ScenarioThree {
    public static void main(String[] args) {
        // initial set up:
        ChromeOptions chromeOptions = new ChromeOptions();


        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);


        String downloadFilePath = "/Users/xuliu/Downloads";

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilePath);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        try {
            driver.get("https://service.northeastern.edu/tech?id=classrooms");
            WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ng-binding[href*='classroom_details']")));
            linkElement.click();
            WebElement pdfLinkElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ng-binding[href*='Hybrid_Nuflex_Classroom.pdf']")));
            pdfLinkElement.click();

            String pdfUrl = driver.getCurrentUrl();
            driver.get(pdfUrl);


        } finally {
            driver.quit();
            System.out.println("End");
        }
    }
}
