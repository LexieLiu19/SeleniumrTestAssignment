package info6255;

import info6255.utils.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class ScenarioTwo {


    public static void testScenarioTwo(WebDriver driver) {
        try {
            // login:
            //  Set the username and password as env variables for Security:
            String userName = System.getenv("username");
            String password = System.getenv("password");


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(2000));
            String scenarioOneScreenshotPath = "screenshots/Two/";
            driver.get("https://northeastern.instructure.com/calendar");

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

            WebElement duoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button")));

            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-3.png");
            duoElement.click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-4.png");
            //stay signed in
            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();
            ScreenshotUtil.takeScreenshot(driver, scenarioOneScreenshotPath + "step-5.png");


            //read csv
            String eventsCSVPath = "src/main/resources/events.csv";
            String delimiter = ",";
            String line = "";
            try (BufferedReader br = new BufferedReader(new FileReader(eventsCSVPath))) {
                while ((line = br.readLine()) != null) {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_new_event_link"))).click();
                    WebElement todoTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"edit_event_tabs\"]/ul/li[2]")));
                    todoTab.click();
                    String[] row = line.split(delimiter);
                    String title = row[0];
                    String time = row[1];
                    String date = row[2];
                    String details = row[3];
                    addEvent(driver, title, date, details, time);


                    //submit
                    WebElement submit = driver.findElement(By.xpath("//*[@id=\"edit_planner_note_form_holder\"]/form/div[2]/button"));
                    submit.click();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            System.out.println("Scenario Two Ends");
        }
    }

    public static void addEvent(WebDriver driver, String title, String date, String details, String time
    ) {

        WebElement titleInput = driver.findElement(By.id("planner_note_title"));
        WebElement dateInput = driver.findElement(By.id("planner_note_date"));
        WebElement timeInput = driver.findElement(By.id("planner_note_time"));

        WebElement detailsInput = driver.findElement(By.id("details_textarea"));

        titleInput.sendKeys(title);
        dateInput.click();
        dateInput.sendKeys(date);

        detailsInput.sendKeys(details);
        timeInput.sendKeys(time);


    }


}
