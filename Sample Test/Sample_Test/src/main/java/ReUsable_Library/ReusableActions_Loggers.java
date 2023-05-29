package ReUsable_Library;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;

public class ReusableActions_Loggers {
    static int timeout = 10;
    WebDriver driver;

    //reusable method to define and launch webdriver
    public static WebDriver setUpDriver() {
        //define the webdriver manager setup for chromedriver
        WebDriverManager.chromedriver().setup();
        //initialize chrome options
        ChromeOptions options = new ChromeOptions();
        //add argument to chrome options
        options.addArguments("start-maximized");
        //for mac use "start-fullscreen"
        //options.addArguments("start-fullscreen");
        //if above does not work on mac, then try --kiosk
        //options.addArguments("--kiosk");
        //initialize driver with chrome options
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }//end of setup driver method

    public static void clickAction(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.click();
            logger.log(LogStatus.PASS, "Successfully clicked on " + elementName);

        } catch (Exception e) {
            System.out.println("Unable to click on element: " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to click on element: " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);


        }
    }//end of click action

    public static void clickActionByIndex(WebDriver driver, String xpath, int index, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))).get(index).click();
            logger.log(LogStatus.PASS, "Successfully clicked on " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to click on element: " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);
        }
    }//end of click action by index

    public static void mouseHover(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //declare mouse actions
        Actions actions = new Actions(driver);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            actions.moveToElement(element).perform();
            logger.log(LogStatus.PASS, "Successfully clicked on " + elementName);

        } catch (Exception e) {
            System.out.println("Unable to hover to element: " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to hover to element: " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);
        }
    }//end of mouse hover reusable action


    public static void sendKeysAction(WebDriver driver, String xpath, String userInput, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.sendKeys(userInput);
            logger.log(LogStatus.PASS, "Successfully able to send keys " + elementName);


        } catch (Exception e) {
            System.out.println("Unable to send keys to : " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to send keys to : " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);
        }
    }//end of send keys Action


    public static String getTextAction(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String textOutput = "";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            textOutput = element.getText();
            logger.log(LogStatus.PASS, "Successfully able to get text for " + elementName);
            logger.log(LogStatus.INFO, "Successfully able to get text for " + elementName + " Text output printed: " + textOutput);

        } catch (Exception e) {
            System.out.println("Unable to get text for : " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to get text for : " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);

        }
        return " "+ textOutput;
    }// end of get Text Action


    public static void submitAction(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.submit();
            logger.log(LogStatus.PASS, "Successfully able click submit for" + elementName);


        } catch (Exception e) {
            System.out.println("Unable to submit on element: " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to submit on element: " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);

        }
    }//end of submit action

    public static void selectbyVisbiletext(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.findElement(By.xpath(xpath));
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(xpath);
            logger.log(LogStatus.PASS, "Successfully able click submit for" + elementName);


        } catch (Exception e) {
            System.out.println("Unable to find dropdown menu");
            logger.log(LogStatus.FAIL, "Unable to find dropdown" + elementName);
            getScreenShot(driver, elementName, logger);

        }
    }// end of select by method for dropdown

    public static void selectbyIndex(WebDriver driver, String xpath, int index, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.findElement(By.xpath(xpath));
            Select dropdown = new Select(element);
            dropdown.selectByIndex(index);
            logger.log(LogStatus.PASS, "Successfully able click submit for" + elementName);


        } catch (Exception e) {
            System.out.println("Unable to find dropdown menu");
            logger.log(LogStatus.FAIL, "Unable to find dropdown" + elementName);
            getScreenShot(driver, elementName, logger);

        }
    }// end of select by index method for dropdown

    public static void SwitchToTabByIndex(WebDriver driver, String xpath, String userValue, ExtentTest logger, String elementName) {
        try {
            WebElement menu = driver.findElement(By.xpath(xpath));
            Select dropDown = new Select(menu);
            dropDown.selectByVisibleText(userValue);

        } catch (Exception e) {
            System.out.println("Unable to select to element " + elementName + " " + e);
            getScreenShot(driver, elementName, logger);
        }//end of try & catch

    }//end of select Text method

    public static String switchTabsByIndex(WebDriver driver, ExtentTest logger, int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return tabs.get(index);
    }//end of swtich tab reuabale action

    public static void scrollByView(WebDriver driver, String xpath, ExtentTest logger, String elementName) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement scrollTo = driver.findElement(By.xpath(xpath));
        jse.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
        scrollTo.click();

    }//end of scrollByView

    public static void scrollByPixel(JavascriptExecutor jse, int pixelsX, int pixelsY) throws InterruptedException {
        Thread.sleep(2000);
        String scroll = "scroll(" + pixelsX + "," + pixelsY + ")";
        jse.executeScript(scroll);
    }

    public static String getTextByIndex(WebDriver driver, String xpath, int Index, ExtentTest logger, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String textOutput = "";
        try {


            ArrayList<WebElement> element = new ArrayList<>(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))));
    textOutput= element.get(Index).getText();
            logger.log(LogStatus.PASS, "Successfully able to get text for " + elementName);
            logger.log(LogStatus.INFO, "Successfully able to get text for " + elementName + " Text output printed" + textOutput);


        } catch (Exception e) {
            System.out.println("Unable to get text for : " + elementName + " for reason: " + e);
            logger.log(LogStatus.FAIL, "Unable to get text for : " + elementName + " for reason: " + e);
            getScreenShot(driver, elementName, logger);
        }

return textOutput;
    }//end of reusable class

    public static void getScreenShot(WebDriver driver, String imageName, ExtentTest logger) {
        try {
            String fileName = imageName + ".png";
            String directory = null;
            String snPath = null;
            directory = "src/main/java/HTML_Report/Screenshots/";
            snPath = "Screenshots//";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture(snPath + fileName);
            logger.log(LogStatus.FAIL, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occured while taking SCREENSHOT!!!");
            e.printStackTrace();
        }
    }//end of getScreenshot method


    public static void compareExpectedAndActualText(String expectedText, String actualText, ExtentTest logger) {
        if (actualText.equals(expectedText)) {
            logger.log(LogStatus.PASS, "Expected Text: " + expectedText + " and Actual Text: " + actualText + " match");
        }
        else{
            logger.log(LogStatus.FAIL, "Expected Text: " + expectedText + " and Actual Text: " + actualText + " does not match");
        }
    }//end of compareExpectedAndActualText

}//end of reusable loggers


