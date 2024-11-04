package Othoba;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Othoba.Listeners.class) // Ensure the full package name is used
public class OthobaTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void forgotPasswordTest() throws InterruptedException {
        driver.get("https://www.othoba.com/login");

        driver.findElement(By.linkText("Forgot password?")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("EmailOrPhone")).sendKeys("01851207570");
        driver.findElement(By.name("send-email")).click();
        Thread.sleep(2000);

        System.out.println("Expected result: Phone number not found");
        String actualResult = driver.findElement(By.tagName("p")).getText();
        System.out.println("Actual result: " + actualResult);

        Assert.assertEquals(actualResult, "Phone Number not found");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
