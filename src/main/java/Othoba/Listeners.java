package Othoba;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed - capturing screenshot for test: " + result.getName());

        // Get the test class instance to access the WebDriver
        OthobaTest testClass = (OthobaTest) result.getInstance();
        WebDriver driver = testClass.getDriver();

        takeScreenshot(driver, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed - capturing screenshot for test: " + result.getName());

        // Get the test class instance to access the WebDriver
        OthobaTest testClass = (OthobaTest) result.getInstance();
        WebDriver driver = testClass.getDriver();

        takeScreenshot(driver, result.getName());
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "/home/pranta/eclipse-workspace/DefectReporting/screenshots/" + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.createDir(new File("screenshots"));  // Create directory if it doesn't exist
            FileHandler.copy(srcFile, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}

