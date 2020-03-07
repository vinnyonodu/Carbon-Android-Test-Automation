package admin;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import utils.TestBase;

public class SignIn extends TestBase {
    @Test
    public void login(String number, String pin) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")).clear();
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")).sendKeys(number);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_pin")).clear();
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_pin")).sendKeys(pin);
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_next")).click();
    }

    @Test
    public void emptyNumberEmptyPinTest() throws Exception {
         String number = "";
         String pin = "";
         String expectedNumberError = "Input your phone number";
         String expectedPinError = "Input PIN";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")));
        Thread.sleep(500);
        String actualNumberError = driver.findElement(By.xpath("//android.widget.TextView[@text='Input your phone number']")).getText();
        String actualPinError = driver.findElement(By.xpath("//android.widget.TextView[@text='Input PIN']")).getText();
        Assert.assertEquals(actualNumberError, expectedNumberError); //Comparing Error displayed  on Phone number field with expected error
        Assert.assertEquals(actualPinError, expectedPinError);  //Comparing Error displayed  on Pin field with expected error
    }

    @Test
    public void validNumberEmptyPinTest() throws Exception {
        String number = "08990001100";
        String pin = "";
        String expectedPinError = "Input PIN";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")));
        Thread.sleep(500);
        String actualPinError = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")).getText();
        Assert.assertEquals(actualPinError, expectedPinError); //Comparing Error displayed  on Pin field with expected error
    }

    @Test
    public void emptyNumberValidPinTest() throws Exception {
        String number = "";
        String pin = "12345";
        String expectedNumberError = "Input your phone number";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")));
        Thread.sleep(500);
        String actualNumberError = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")).getText();
        Assert.assertEquals(actualNumberError, expectedNumberError);  //Comparing Error displayed  on Phone number field with expected error
    }

    @Test
    public void validNumberIncorrectPinTest() throws Exception {
        String number = "08990001100";
        String pin = "1267";
        String expectedPinError = "Error";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Error']")));
        Thread.sleep(1000);
        String actualPinError = driver.findElement(By.xpath("//android.widget.TextView[@text='Error']")).getText();
        Assert.assertEquals(actualPinError, expectedPinError);//To compare expected error with actual erro displayed for incorrect pin
        driver.findElement(By.id("android:id/button1")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/sign_in_phone")));
    }

    @Test
    public void invalidNumberLengthValidPinTest() throws Exception {
        String number = "08990";
        String pin = "1234";
        String expectedNumberError = "Incorrect phone format";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")));
        Thread.sleep(500);
        String actualNumberError = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")).getText();
        Assert.assertEquals(actualNumberError, expectedNumberError);
    }

    @Test
    public void validNumberInvalidPinLengthTest() throws Exception {
        String number = "08990001100";
        String pin = "123";
        String expectedPinError = "Must be a 4-digit PIN";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")));
        Thread.sleep(500);
        String actualPinError = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/textinput_error")).getText();
        Assert.assertEquals(actualPinError, expectedPinError);
    }

    @Test
    public void validNumberValidPinTest() throws Exception  {
        String number = "08990001100";
        String pin = "1234";
        String verificationNumber = "123456";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        login(number, pin);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Verify your phone number']")));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//android.widget.ScrollView[@index='2']")).sendKeys(verificationNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/tvRightNow")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/tvRightNow")).click();
    }

}
