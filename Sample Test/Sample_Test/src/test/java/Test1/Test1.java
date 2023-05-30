package Test1;

import ReUsable_Library.ReusableActions;
import ReUsable_Library.ReusableActions_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

public class Test1 {


    WebDriver driver;
    ExtentReports reports;
    ExtentTest logger;

    @BeforeSuite
    public void setDriver() {
        driver = ReusableActions.setUpDriver();
        reports = new ExtentReports("src/main/java/HTML_Report/Automation_Report.html", true);
    }

    @AfterSuite
    public void quitSession() {
        //driver.quit();
        reports.flush(); //writing the logs back to the report
    }

    @Test(priority = 1)
    public void tc001_completeApplication() throws InterruptedException {
        //set the name of the test case to the report by using logger concept
        logger = reports.startTest("tc001_completeApplication");

        //first navigate to webpage homepage
        driver.navigate().to("https://www.portfolioanalyst.com/en/pa/home.php");
        Thread.sleep(3000);
//next we need to click on get started button
        ReusableActions_Loggers.clickAction(driver, "//*[@class='btn btn-primary order-0 order-lg-1']", logger, "Get STarted Button");
//Clicking on Create Account Button
        ReusableActions_Loggers.clickActionByIndex(driver, "//*[@class='btn btn-primary']", 5, logger, "Create Account");
        Thread.sleep(2000);
        //to validate url
        String url = driver.getCurrentUrl();
        ReusableActions_Loggers.compareExpectedAndActualText("https://ndcdyn.interactivebrokers.com/Universal/Application?pa=T", url, logger);

//Entering Email
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='emailAddress']", "QAGtesterAutomation@gmail.com", logger, "Email Adress");
//to randomly Generate username we create Array for username
        String[] username = {"QAEngineer", "QATester", "QAAnalyst", "QAAutomation"};
        Integer[] numbers = {412122, 3411111, 235345, 2342, 4324, 45248, 6749, 2934};
        Random random = new Random();
        String randomUsername = (username[random.nextInt(username.length)] + numbers[random.nextInt(numbers.length)]);
        System.out.println(randomUsername);
Thread.sleep(500);
        //To enter random generated Username
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='username']", randomUsername, logger, "Username Field");
        //select from the list of suggested usernames if one is already taken
        ReusableActions_Loggers.clickActionByIndex(driver,"//*[@class='btn btn-secondary btn-sm']",1,logger,"suggested name");

        //to enter password
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password']","tester12",logger,"password button");
Thread.sleep(1000);
        //to confirm password
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password2']","tester12",logger,"password confirm button");

    // Randomly select country
       // ReusableActions_Loggers.clickAction(driver,"//*[@class='chosen-single']",logger,"Counrty");
Thread.sleep(1000);

// Randomly select country
        ReusableActions_Loggers.clickAction(driver, "//*[@class='chosen-single']", logger, "country");
        Thread.sleep(1000);
        ArrayList<WebElement> countries= new ArrayList<>(driver.findElements(By.xpath("//*[@class='active-result']")));
        Thread.sleep(1000);
        countries.get(random.nextInt(countries.size())).click();
        Thread.sleep(2000);
        //handle popup radio buttons
        ReusableActions_Loggers.clickAction(driver,"//*[@class='iradio_square-blue']",logger,"Marketing Radio Button");
        //countries.get(5).click();
        //countries.get(random.nextInt(countries.size())).click();

//scroll down
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='answer2']",logger,"Security Question scroll");
Thread.sleep(500);
        //Enter First name
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='firstName']","John",logger,"Firstname");
//Enter Last name
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='lastName']","Doe",logger,"Last Name Input");
        //Enter DOB as o1/01/2001
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='date']","01012001",logger,"DOB");
   //Security Q1 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question0']",3,logger,"Q1");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer0']","Target",logger,"Answer1");

        //security Q2 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question1']",6,logger,"Q2");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer1']","NY Knicks",logger,"Answer2");

        //Security Q3 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question2']",15,logger,"Q3");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer2']","Dubai",logger,"Answer3");

        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_positive']",logger,"Security Question scroll");

        //click on Agreement
        Thread.sleep(600);
        ReusableActions_Loggers.clickActionByIndex(driver,"//*[@aria-expanded='false']",2,logger,"Agreement Button");
        Thread.sleep(500);
        ReusableActions_Loggers.getTextAction(driver,"//*[@class='card card-body']",logger,"get text");

        Thread.sleep(500);
        ReusableActions_Loggers.getScreenShot(driver,"Agreement",logger);
//Click on Agree
        Thread.sleep(1000);
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_positive']",logger,"Agreement scroll");
Thread.sleep(1000);
        ReusableActions_Loggers.clickAction(driver,"//*[@id='paAgreement_positive']",logger,"Agree Button");
        //Click on Create Account
        Thread.sleep(1000);
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_positive']",logger,"Agreement scroll");

       ReusableActions_Loggers.clickAction(driver,"//*[@class='btn btn-lg btn-primary w-100 btn-create']",logger,"Create Account Button");
//Validate that email confirmation page loads
       Thread.sleep(5000);
       ReusableActions_Loggers.getScreenShot(driver,"Email Confirmation",logger);

    }//end of test 1


    @Test(priority = 2)
    public void tc002_FieldValidationsForApplication() throws InterruptedException {
        logger = reports.startTest("tc002_FieldValidationsForApplication");

        //first navigate to webpage homepage
        driver.navigate().to("https://www.portfolioanalyst.com/en/pa/home.php");
        Thread.sleep(3000);

        //next we need to click on get started button
        ReusableActions_Loggers.clickAction(driver, "//*[@class='btn btn-primary order-0 order-lg-1']", logger, " Get STarted Button");
//Clicking on Create Account Button
        ReusableActions_Loggers.clickActionByIndex(driver, "//*[@class='btn btn-primary']", 5, logger, " Create Account");
        Thread.sleep(2000);

        //Entering incorrect Email to Negative Test
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='emailAddress']", "WrongEmail", logger, " Email Adress");

        //Entering Invalid Username to Negative Test
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='username']", "##@@", logger, " Username Field");
       Thread.sleep(500);
        //capture Email Error Message
        ReusableActions_Loggers.getTextAction(driver,"//*[@class='alert alert-danger']",logger," Email Error Message");
        ReusableActions_Loggers.getScreenShot(driver,"Invalid Username",logger);

        //to enter password
     //   ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password']","***",logger," password button");
        Thread.sleep(1000);
        ArrayList<String> passCheck=new ArrayList<>();
        passCheck.add("pwCheck0");
        passCheck.add("pwCheck1");
        passCheck.add("pwCheck2");
        passCheck.add("pwCheck3");

        ArrayList<String> password=new ArrayList<>();
        password.add("##@@");
        password.add("james");
        password.add("12345678");
        password.add("helloWorld");
      //  ReusableActions_Loggers.clickAction(driver,"//*[@id='passwordView']",logger,"view password");

    for(int i=0; i<passCheck.size();i++){
            ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password']",password.get(i),logger," password button");
        Thread.sleep(2000);
        ReusableActions_Loggers.getScreenShot(driver,"Invalid Username "+(i+1),logger);

       //ReusableActions_Loggers.clickAction(driver,"//*[@id='passwordView']",logger,"view password");
            String color = driver.findElement(By.xpath("//*[@id='"+passCheck.get(i)+"']")).getCssValue("color");
            if(color.equals("rgba(188, 16, 16, 1)")){
                System.out.println(color);
                logger.log(LogStatus.PASS,"Password Error Message Found for: "+passCheck.get(i)+" and for password: "+password.get(i));
            }
        driver.findElement(By.xpath("//*[@id='password']")).clear();

    }

      //getting screenshot to validate that an error is shown when non matching passwords are entered and United States is preselcted for dropdown
      ReusableActions_Loggers.getScreenShot(driver,"Invalid password",logger);

        //clear invalid fields to enter valid fields
        driver.findElement(By.xpath("//*[@id='password']")).clear();
        driver.findElement(By.xpath("//*[@id='emailAddress']")).clear();
        driver.findElement(By.xpath("//*[@id='username']")).clear();
        Thread.sleep(500);


      //Entering proper Email/username and password but leaving the rest of required fields blank to capture error messages
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='emailAddress']", "QAGUY@gmail.com", logger, "Email Adress");
        //entering username
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='username']","LebronJames2023" , logger, "Username Field");

        //to enter password
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password']","tester12",logger,"password button");
        Thread.sleep(500);
        //to confirm password
       ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password2']","tester12",logger,"password confirm button");
Thread.sleep(500);
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_positive']",logger,"Agreement scroll");

        Thread.sleep(2000);
          //clicking on create account button to invoke an error for required fields
        ReusableActions_Loggers.clickAction(driver,"//*[@class='btn btn-lg btn-primary w-100 btn-create']",logger,"Create Account Button");
Thread.sleep(2000);
      //capturing Error Messages
        ReusableActions_Loggers.getScreenShot(driver,"Invalid password",logger);
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",0,logger,"first name");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",1,logger,"Last name");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",2,logger,"Date of Birth");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",3,logger,"Security Q1 ");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",4,logger,"Answer 1");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",5,logger,"Security Q2");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",6,logger,"Answer 2");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",7,logger,"Security Q3");
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",8,logger,"Answer 3");



    }//end of test 2
    @Test(priority = 3)
    public void tc003_agreementToogleTest() throws InterruptedException {
        //set the name of the test case to the report by using logger concept
        logger = reports.startTest("tc003_agreementToogleTest");

        //first navigate to webpage homepage
        driver.navigate().to("https://www.portfolioanalyst.com/en/pa/home.php");
        Thread.sleep(3000);
//next we need to click on get started button
        ReusableActions_Loggers.clickAction(driver, "//*[@class='btn btn-primary order-0 order-lg-1']", logger, "Get STarted Button");
//Clicking on Create Account Button
        ReusableActions_Loggers.clickActionByIndex(driver, "//*[@class='btn btn-primary']", 5, logger, "Create Account");
        Thread.sleep(2000);
        //to validate url
        String url = driver.getCurrentUrl();
        ReusableActions_Loggers.compareExpectedAndActualText("https://ndcdyn.interactivebrokers.com/Universal/Application?pa=T", url, logger);

//Entering Email
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='emailAddress']", "QAGtesterAutomation@gmail.com", logger, "Email Adress");
//to randomly Generate username we create Array for username
        String[] username = {"QAEngineer", "QATester", "QAAnalyst", "QAAutomation"};
        Integer[] numbers = {412122, 3411111, 235345, 2342, 4324, 45248, 6749, 2934};
        Random random = new Random();
        String randomUsername = (username[random.nextInt(username.length)] + numbers[random.nextInt(numbers.length)]);
        System.out.println(randomUsername);
        Thread.sleep(500);
        //To enter random generated Username
        ReusableActions_Loggers.sendKeysAction(driver, "//*[@id='username']", randomUsername, logger, "Username Field");
       //click on the password box
        ReusableActions_Loggers.clickAction(driver,"//*[@id='password']",logger,"password button");
        Thread.sleep(1000);
        //select from the list of suggested usernames if one is already taken
        ReusableActions_Loggers.clickActionByIndex(driver,"//*[@class='btn btn-secondary btn-sm']",1,logger,"suggested name");

        //to enter password
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password']","tester12",logger,"password button");
        Thread.sleep(1000);
        //to confirm password
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='password2']","tester12",logger,"password confirm button");

        Thread.sleep(1000);

// Randomly select country
        ReusableActions_Loggers.clickAction(driver, "//*[@class='chosen-single']", logger, "country");
        Thread.sleep(1000);
        ArrayList<WebElement> countries= new ArrayList<>(driver.findElements(By.xpath("//*[@class='active-result']")));
        Thread.sleep(1000);
        countries.get(random.nextInt(countries.size())).click();
        Thread.sleep(2000);
        //handle popup radio buttons if they appear
        ReusableActions_Loggers.clickAction(driver,"//*[@class='iradio_square-blue']",logger,"Marketing Radio Button");
        

//scroll down
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='answer2']",logger,"Security Question scroll");
        Thread.sleep(500);
        //Enter First name
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='firstName']","John",logger,"Firstname");
//Enter Last name
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='lastName']","Doe",logger,"Last Name Input");
        //Enter DOB as o1/01/2001
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='date']","01012001",logger,"DOB");
        //Security Q1 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question0']",3,logger,"Q1");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer0']","Target",logger,"Answer1");

        //security Q2 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question1']",6,logger,"Q2");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer1']","NY Knicks",logger,"Answer2");

        //Security Q3 and Answer
        ReusableActions_Loggers.selectbyIndex(driver,"//*[@id='question2']",15,logger,"Q3");
        ReusableActions_Loggers.sendKeysAction(driver,"//*[@id='answer2']","Dubai",logger,"Answer3");

        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_negative']",logger,"Security Question scroll");
        Thread.sleep(500);

//Click on DisAgree
        Thread.sleep(1000);
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_negative']",logger,"Agreement scroll");
        Thread.sleep(1000);
        ReusableActions_Loggers.clickAction(driver,"//*[@id='paAgreement_negative']",logger,"Agree Button");
        //Click on Create Account
        Thread.sleep(1000);
        ReusableActions_Loggers.clickAction(driver,"//*[@id='paAgreement_negative']",logger,"Disagree");
        //attempt to create an account
        ReusableActions_Loggers.clickAction(driver,"//*[@class='btn btn-lg btn-primary w-100 btn-create']",logger,"Create Account Button");
        Thread.sleep(1000);
        //click out of the username box then scroll
        ReusableActions_Loggers.clickAction(driver,"//*[@class='col-12 col-lg-6 start-right']",logger,"right side box");
        //scroll down to see error message
        ReusableActions_Loggers.scrollByView(driver,"//*[@id='paAgreement_negative']",logger,"Agreement scroll");
        //verify error message
        ReusableActions_Loggers.getTextByIndex(driver,"//*[@class='alert alert-danger']",0,logger,"Agree to terms");
        //take a screenshot
        ReusableActions_Loggers.getScreenShot(driver,"Agree Message",logger);

    }//end of test 3


}//end of class

