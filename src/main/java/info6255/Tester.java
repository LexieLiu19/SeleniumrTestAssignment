package info6255;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        WebDriver driver = new ChromeDriver(chromeOptions);
//
//        try {
//            driver.get("https://neuidmsso.neu.edu/idp/profile/cas/login");
//
//        } finally {
//            System.out.println("end");
//        }

        //read csv test:
        String eventsCSVPath = "src/main/resources/events.csv";
        String delimiter = ",";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(eventsCSVPath))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                String title = row[0];
                String time = row[1];
                String date = row[2];
                String details = row[3];
                System.out.println(title + " - " + date + " - " + time + " - " + details);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}