package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    int maxRetries = 3;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    BaseTest baseTest = new BaseTest();

    public void click(WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                System.out.println(getCurrentTimeDate() + " Clicked: " + log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: " + retryCount + " to click on " + log);
                if (retryCount == maxRetries) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    baseTest.reportScreenshot("failedClick", "Failed to click");
                    throw new Exception(getCurrentTimeDate() + " Failed to click element: " + log);
                }
            }
        }
    }

    public void typeText(WebElement element, String text, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(text);
                System.out.println(getCurrentTimeDate() + " Typed: " + text + " - " + log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: " + retryCount + " to type to " + log);
                if (retryCount == maxRetries) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    baseTest.reportScreenshot("failedClick", "Failed to click");
                    throw new Exception(getCurrentTimeDate() + " Failed to type to element: " + log);
                }
            }
        }
    }

    public void typeText(WebElement element, Keys key, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(key);
                System.out.println(getCurrentTimeDate() + " Typed: " + key + " - " + log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: " + retryCount + " to type to " + log);
                if (retryCount == maxRetries) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    baseTest.reportScreenshot("failedClick", "Failed to click");
                    throw new Exception(getCurrentTimeDate() + " Failed to type to element: " + log);
                }
            }
        }
    }

    public void assertEQ(String actual, String expected, String log) {
        Assert.assertEquals(actual, expected);
        System.out.println(getCurrentTimeDate() + " Verified: " + log);
    }

    public String getCurrentTimeDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
