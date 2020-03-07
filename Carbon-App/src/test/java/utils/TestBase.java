package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TestBase {

       public WebDriver driver;


    @BeforeClass
    public void startApp() throws Exception {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("BROWSER_NAME", "Android");
            capabilities.setCapability("deviceName", "Samsung Galaxy j7");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appPackage", "com.lenddo.mobile.paylater.staging");
            capabilities.setCapability("appActivity", "com.lenddo.mobile.paylater.home.activity.HomeActivity");
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("resetKeyboard", true);

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            System.out.println("++++++++++CARBON APP LAUNCHED++++++++++++");


        } catch (WebDriverException e) {
          System.out.println("System error: " + e);
        }
    }


    @Test
    public void skipToSignIn() throws Exception {
        //To Skip to login page
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/tutorial_skip")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/tutorial_skip")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button")));
        Thread.sleep(500);
        driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/user_type_existing")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/user_type_existing")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        Thread.sleep(500);
    }

    @Test
    public void generalLogin() throws Exception {
        //All Other Classes will use this method for their login activity

        WebDriverWait wait = new WebDriverWait(driver, 60);

        String number = "08990001100";
        String pin = "1234";
        String verificationNumber = "123456";

        skipToSignIn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")).sendKeys(number);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_pin")).sendKeys(pin);
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_next")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Verify your phone number']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.ScrollView[@index='2']")).sendKeys(verificationNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/tvRightNow")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/tvRightNow")).click();

    }

    @AfterClass
    public void closeApp() {

        driver.quit();
    }
}
