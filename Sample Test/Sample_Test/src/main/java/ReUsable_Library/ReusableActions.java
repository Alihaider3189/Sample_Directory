package ReUsable_Library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class ReusableActions {
    public static int timeout= 60;
    WebDriver driver;
    //reusable method to define and launch webdriver
    public static WebDriver setUpDriver() {
        //define the webdriver manager setup for chromedriver
        WebDriverManager.chromedriver().setup();
        //initialize chrome options
        ChromeOptions options = new ChromeOptions();
        //add argument to chrome options
        options.addArguments("start-maximized","incognito");
        //for mac use "start-fullscreen"
        //options.addArguments("start-fullscreen");
        //if above does not work on mac, then try --kiosk
        //options.addArguments("--kiosk");
        //initialize driver with chrome options
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }//end of setup driver method

    public static void clickAction(WebDriver driver, String xpath, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.click();
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + elementName + " for reason: " + e);
        }
    }

    public static void clickActionByIndex(WebDriver driver, String xpath, int index, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))).get(index).click();
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + elementName + " for reason: " + e);
        }
    }//end of click action by index

    public static void MouseHover(WebDriver driver, String xpath, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //declare mouse actions
        Actions actions = new Actions(driver);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Unable to hover to element: " + elementName + " for reason: " + e);
        }
    }

    public static void sendKeysAction(WebDriver driver, String xpath, String userInput, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.sendKeys(userInput);
        } catch (Exception e) {
            System.out.println("Unable to send keys to : " + elementName + " for reason: " + e);
        }
    }//end of send keys Action

    public static String getTextAction(WebDriver driver, String xpath, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String textOutput = "";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            textOutput = element.getText();
        } catch (Exception e) {
            System.out.println("Unable to get text for : " + elementName + " for reason: " + e);
        }
        return textOutput;
    }// end of get Text Action


        public static void submitAction(WebDriver driver, String xpath, String elementName) {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                element.submit();
            } catch (Exception e) {
                System.out.println("Unable to submit on element: " + elementName + " for reason: " + e);
            }
        }//end of submit action

    public static void selectbytext(WebDriver driver, String xpath, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.findElement(By.xpath(xpath));
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(xpath);
        } catch (Exception e) {
            System.out.println("Unable to find dropdown menu");
        }
    }
        public static void SelectTextByVisibleText(WebDriver driver, String xpath, String userValue, String elementName){
            try {
                WebElement menu = driver.findElement(By.xpath(xpath));
                Select dropDown = new Select(menu);
                dropDown.selectByVisibleText(userValue);
            }catch(Exception e){
                System.out.println("Unable to select to element " +elementName+ " " +e);
            }//end of try & catch

    }//end of select Text method
    public static String switchTabsByIndex(WebDriver driver, int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return tabs.get(index);
    }

    public static void scrollByView(WebDriver driver,String xpath, String elementName) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement scrollTo = driver.findElement(By.xpath(xpath));
            jse.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
            scrollTo.click();

        }//end of scrollByView
    }//end of reusable class
