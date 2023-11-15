package info6255;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.HashMap;

public class TestRunner {

    public static void main(String[] args) {

        //file path variables:
        String currentDir = System.getProperty("user.dir");
        String downloadFilePath = Paths.get(currentDir, "downloadPDF").toString();


        // initial set up:
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        HashMap<String, Object> chromePrefs = new HashMap<>();

        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilePath);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("printing.print_preview_sticky_settings.appState", "{\"recentDestinations\": [{\"id\": \"Save as PDF\",\"origin\": \"local\",\"account\": \"\"}],\"selectedDestinationId\": \"Save as PDF\",\"version\": 2}");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--kiosk-printing");

        //start driver:
        WebDriver driver = new ChromeDriver(chromeOptions);
        //test for S1:
        ScenarioOne.testScenarioOne(driver);
        //test for S3:
        ScenarioThree.testScenarioThree(driver);

        driver.quit();
        System.out.println("Finished!");


    }
}
