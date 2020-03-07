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

public class FundWallet extends TestBase {

    @Test
    public void emptyAmountFieldTest() throws Exception {
        String expectedAmountQuestion = "How much do you want to fund?";
        String amount = "";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")).sendKeys(amount);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/proceedWalletFunding")).click();
        Thread.sleep(500);
        String actualAmountQuestion = driver.findElement(By.xpath("//android.widget.TextView[@text='How much do you want to fund?']")).getText();
        Assert.assertEquals(expectedAmountQuestion, actualAmountQuestion); //App remains constant on the amount page thus performing no action upon proceed button click

    }

    @Test
    public void alphabetOnAmountFieldTest() throws Exception {
        String expectedAmountQuestion = "How much do you want to fund?";
        String alphabetAmount = "1000a";
        String amount = "1,000";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")).sendKeys(alphabetAmount);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/proceedWalletFunding")).click();
        Thread.sleep(500);
        String displayedAmountValue = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")).getText();
        Assert.assertNotEquals(alphabetAmount, displayedAmountValue); //confirming that the alphabet was not accepted by the amount inout field
        Assert.assertEquals(amount, displayedAmountValue);
        String actualAmountQuestion = driver.findElement(By.xpath("//android.widget.TextView[@text='How much do you want to fund?']")).getText();
        Assert.assertEquals(expectedAmountQuestion, actualAmountQuestion); //App remains constant on the amount page thus performing no action upon proceed button click

    }

    @Test
    public void validAmountTest() throws Exception {
        String amount = "1000";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fundWalletCard")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/walletAmountToFund")).sendKeys(amount);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/proceedWalletFunding")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@index='0']")));
    }

    @Test
    public void attemptToProceedWithoutCardSelection() throws Exception {
        String expectedCard = "408408****4081";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/button_confirm_payment")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_confirm_payment")).click();
        Thread.sleep(500);
        String actualCard = driver.findElement(By.xpath("//android.widget.TextView[@text='408408****4081']")).getText();
        Assert.assertEquals(expectedCard, actualCard); //confirming that app is still on the same page thus not performing any given action and further confirming disability
    }

    @Test
    public void validCardTest() throws Exception {
        String expectedAccountNumber = "1352826814";
        String expectedAccountHolder = "Femi Ajayi";
        String expectedAmount = "1,000";
        String expectedServiceCharge = "10";
        String expectedTotalPayment = "1,010";
        String expectedSecuredPayed = "Securely pay ₦1,010";
        String pin = "1234";
        String expectedSuccessMessage = "Your wallet top-up was successful";
        String expectedFinalAmount = "₦1,000";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.FrameLayout[@index='0']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_confirm_payment")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/text_view_account_number")));
        Thread.sleep(500);
        String actualAccountNumber = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_account_number")).getText();
        String actualAccountHolder = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_account_name")).getText();
        String actualAmount = driver.findElement(By.xpath("//android.widget.TextView[@text='1,000']")).getText();
        String actualServiceCharge = driver.findElement(By.xpath("//android.widget.TextView[@text='10']")).getText();
        String actualTotalPayment =  driver.findElement(By.xpath("//android.widget.TextView[@text='1,010']")).getText();
        String actualSecuredPayed = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_text_secure_pay")).getText();
        Assert.assertEquals(expectedAccountNumber, actualAccountNumber);
        Assert.assertEquals(expectedAccountHolder, actualAccountHolder);
        Assert.assertEquals(expectedAmount, actualAmount);
        Assert.assertEquals(expectedServiceCharge, actualServiceCharge);
        Assert.assertEquals(expectedTotalPayment, actualTotalPayment);
        Assert.assertEquals(expectedSecuredPayed, actualSecuredPayed);
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_text_secure_pay")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView[@index='1']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.ScrollView[@index='1']")).sendKeys(pin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Your wallet top-up was successful']")));
        Thread.sleep(500);
        String  actualSuccessMessage = driver.findElement(By.xpath("//android.widget.TextView[@text='Your wallet top-up was successful']")).getText();
        Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage); //to confirm appropraite success message
        String actualFinalAmount = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/value_amount")).getText();
        Assert.assertEquals(expectedFinalAmount, actualFinalAmount); //to confirm appropriate transaction amount
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/success_home_button")).click();
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/okayButton")).click(); //Okay button on final transaction success modal



    }
}
