package testsuite;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 2. Create the package ‘testsuite’ and create the
 * following class inside the ‘testsuite’ package.
 * 1. LoginTest
 * 3. Write down the following test into ‘LoginTest’ class
 * 1. userSholdLoginSuccessfullyWithValidCredentials
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the text “Secure Area”
 * 2. verifyTheUsernameErrorMessage
 * * Enter “tomsmith1” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your username
 * is invalid!”
 * 3. verifyThePasswordErrorMessage
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your password
 * is invalid!”
 */
public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";         //Base URL

    @Before
    public void setUp() {               //Browser open code
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {              //Login test
        driver.findElement(By.id("username")).sendKeys("tomsmith");                                    //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");                        //Enter password
        driver.findElement(By.xpath("//button[@class='radius']")).click();                           //Click on login button
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2[text()=' Secure Area']")).getText();   //Getting text to verify
        Assert.assertEquals("User is not logged in with valid credentials", expectedText, actualText);    //Comparing 2 strings
    }

    @Test
    public void verifyTheUsernameErrorMessage() {              //Login test with invalid username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");                                    //Enter invalid username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");                        //Enter password
        driver.findElement(By.xpath("//button[@class='radius']")).click();                           //Click on login button
        String expectedText = "Your username is invalid!";
        String actualText = driver.findElement(By.id("flash")).getText().substring(0,25);                                                                      //Getting text to verify
        Assert.assertEquals("User is not logged in with invalid credentials", expectedText, actualText);    //Comparing 2 strings
    }

    @Test
    public void verifyThePasswordErrorMessage() {               //Login with invalid password test
        driver.findElement(By.id("username")).sendKeys("tomsmith");                                    //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");                         //Enter invalid password
        driver.findElement(By.xpath("//button[@class='radius']")).click();                           //Click on login button
        String expectedText = "Your password is invalid!";
        //Getting text to verify
        String actualText = driver.findElement(By.xpath("//div[@id='flash' and contains(text(), 'Your password is invalid!')]")).getText().substring(0,25);
        Assert.assertEquals("User is not logged in with invalid credentials", expectedText, actualText);    //Comparing 2 strings}
    }

    public void tearDown() {                    //Browser closing code
        closeBrowser();
    }
}
