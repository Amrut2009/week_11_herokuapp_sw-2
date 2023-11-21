package testsuite;

import browserfactory.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseurl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup() {
        openbrowser(baseurl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCdentiaLS() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals( "secure area",expectedText, actualText);

    }

    @Test
    public void verifyusernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("rutu");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//button[@type='submit'] ")).click();
        String expectedText = "Your username is invalid!\n"+"×";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("message display: your username is invalid",actualText, expectedText);
    }

    @Test
    public void verifyThePasswordErrorMessge() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("Super!");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectedText = "Your password is invalid!\n"+"×";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("message display:your password is invalid", expectedText, actualText);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}

