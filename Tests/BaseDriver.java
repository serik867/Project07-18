package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseDriver {

    public  WebDriver driver;
    public  WebDriverWait wait;
    public  JavascriptExecutor js;

    @BeforeClass
    public void method1() {
        System.setProperty( "webdriver.chrome.driver", "/Users/serdur/Desktop/TechnoStudy/Selenium/ChromeDriver/chromedriver");
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        wait = new WebDriverWait( driver,2000 );
        js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );

        driver.get( "https://app.hubspot.com/login" );
        WebDriverWait wait = new WebDriverWait( driver, 5 );

        By emailinput = By.id("username");
        wait.until( ExpectedConditions.visibilityOfElementLocated(emailinput));

        //        Enter the user name and password click on login button
        driver.findElement(emailinput).sendKeys("durbayevserdar@hotmail.com");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("Hubspot12345");
        driver.findElement(By.id("loginBtn")).click();

    }

    @Parameters("TitleName")
    @AfterClass
    public void deleteMethod(String title) throws InterruptedException {
           driver.findElement( By.xpath( "//span[text()='Actions']" ) ).click();
           driver.findElement( By.xpath( "//i18n-string[text()='Delete']" ) ).click();
           driver.findElement( By.xpath( "//button[@data-confirm-button='accept']" ) ).click();
           logout(wait,title);
       }


    private void logout(WebDriverWait wait,String title) throws InterruptedException {
        wait.until(ExpectedConditions.titleIs(title));
        driver.findElement(By.cssSelector("a[id=\"account-menu\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
        WebElement signout = driver.findElement(By.linkText("Sign out"));
        signout.click();
        driver.quit();
    }
}

