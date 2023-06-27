package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test

    public void userShouldLoginSuccessfullyWithValidCredentials(){
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.xpath("//button[@class='radius']")).click();

        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//div[@id='content']/div/h2")).getText();
        Assert.assertEquals(actualText,expectedText);
    }
    @Test

    public void verifyTheUsernameErrorMessage(){
        driver.findElement(By.name("username")).sendKeys("tomsmith1");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.xpath("//button[@class='radius']")).click();

        // Verify the error message “Your username is invalid!”
        String expectedText = "Your username is invalid!\n" +
                "×";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals(expectedText, actualText);

    }

    @Test

    public void verifyThePasswordErrorMessage(){
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");

        driver.findElement(By.xpath("//button[@class='radius']")).click();

        //Verify the error message “Your password is invalid!”
        String expectedText = "Your password is invalid!\n" +
                "×";
        String actualText = driver.findElement(By.xpath("//div[@id='flash'][contains(@class, 'error')]")).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
