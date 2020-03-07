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

public class AirtimeRecharge extends TestBase{

    @Test
    public void savedAirtimeRecharge() throws Exception {
        String expectedPageMessageAirtime = "What phone number would you like to recharge?"; //Message displayed on the Airtime Recharge introduction page
        String expectedPageMessageSaved = "Saved Payments";
        String expectedSavedName = "Airtel 08123430728"; //Value of the saved name
        String expectedPhoneNumber = "08123430728";     //Value of the saved phone number
        String expectedAmount = "200";          //Value of the saved amount
        String expectedDate = "Jul 12, 2019";       //Value of the saved date
        String expectedServiceProvider = "Airtel";
        String securedPaymentValue = "Securely pay ₦ 200.00";
        String expectedConfirmationMessage = "You've just recharged 08123430728 with ₦200 airtime.";
        WebDriverWait wait = new WebDriverWait(driver, 60);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='0']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        String actualPageMessageAirtime = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_1")).getText();
        Assert.assertEquals(expectedPageMessageAirtime, actualPageMessageAirtime);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Saved']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/fontTextView7")));
        Thread.sleep(500);
        String actualPageMessageSaved = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fontTextView7")).getText();
        Assert.assertEquals(expectedPageMessageSaved, actualPageMessageSaved);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/recycler_view_saved")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/text_view_saved_name")));
        Thread.sleep(500);
        String actualSavedName = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_saved_name")).getText();
        String actualPhoneNumber = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_phone_number")).getText();
        String actualAmount = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/amount_view_airtime_price")).getText();
        String actualDate = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_saved_date")).getText();
        Assert.assertEquals(expectedSavedName, actualSavedName);  //Comparing saved name displayed  with expected saved name
        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber); //Comparing Phone number displayed  with expected Phone number
        Assert.assertEquals(expectedAmount, actualAmount); //Comparing amount displayed  with expected actual amount
        Assert.assertEquals(expectedDate, actualDate); //Comparing date displayed  with expected date
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_pay_again")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@index='1']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.FrameLayout[@index='1']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.FrameLayout[@index='0']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_confirm_payment")).click();
        String finalPhoneNumber = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_phone_number")).getText();
        String finalAmount = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/amount_view_amount")).getText();
        String finalDate = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_saved_date")).getText();
        String finalServiceProvider = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_mobile_network")).getText();
        String securelyPay = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_text_secure_pay")).getText(); //secured payment amount displayed on button
        Thread.sleep(500);
        Assert.assertEquals(expectedServiceProvider, finalServiceProvider);  //Comparing saved name displayed  with expected saved name
        Assert.assertEquals(expectedPhoneNumber, finalPhoneNumber); //Comparing Phone number displayed  with expected Phone number
        Assert.assertEquals(expectedAmount, finalAmount); //Comparing amount displayed  with expected actual amount
        Assert.assertEquals(securelyPay, securedPaymentValue); //Verifying the value on the securely pay button
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/successMessageText")));
        Thread.sleep(500);
        String actualConfirmationMessage = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/successMessageText")).getText();
        Assert.assertEquals(expectedConfirmationMessage, actualConfirmationMessage); //Comparing expected confirmation message with actual confirmation message
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/airtimeItem")).click();

    }
    @Test
    public void emptyPhoneNumberFieldTest() throws Exception {
        String expectedPageMessageAirtime = "What phone number would you like to recharge?"; //Message displayed on the Airtime Recharge introduction page
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='0']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        Thread.sleep(1000);
        String actualPageMessageAirtime = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_1")).getText();
        Assert.assertEquals(expectedPageMessageAirtime, actualPageMessageAirtime); //To verify that the page remains constant regardless if the next button click thus confirming disabled status
    }
    @Test
    public void incompletePhoneNumberTest() throws Exception {
        String incompletePhoneNumber = "0812343072";
        String expectedPageMessageAirtime = "What phone number would you like to recharge?"; //Message displayed on the Airtime Recharge introduction page
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")).sendKeys(incompletePhoneNumber);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        Thread.sleep(1000);
        String actualPageMessageAirtime = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_1")).getText();
        Assert.assertEquals(expectedPageMessageAirtime, actualPageMessageAirtime); //To verify that the page remains constant regardless if the next button click thus confirming disabled status
    }
    @Test
    public void alphabetCheckOnPhoneNumberField() throws Exception {
        String alphabetAndPhoneNumber = "0812343072a";
        String phoneNumber = "08123430728";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")).sendKeys(alphabetAndPhoneNumber);
        Thread.sleep(500);
        String phoneNumberFieldValue = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")).getText();
        Assert.assertNotEquals(phoneNumberFieldValue, alphabetAndPhoneNumber); //To confirm that the value displayed on the field does not include the alphabet
        Assert.assertEquals(phoneNumber, phoneNumberFieldValue); //To confirm that the value displayed on the field does not include the alphabet
    }
    @Test
    public void validPhoneNumberTest() throws Exception {
        String validPhoneNumber = "08123430728";
        String expectedAmountQuestion = "How much airtime do you want to buy?";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")).sendKeys(validPhoneNumber);
        Thread.sleep(500);
        String actualAmountQuestion = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/fontTextView16")).getText();
        Assert.assertNotEquals(expectedAmountQuestion, actualAmountQuestion); //To confirm that the valid phone number introduced he amount environment
    }
    @Test
    public void selectAmountFromDisplayedSnippetTest() throws Exception {
        String validPhoneNumber = "08123430728";
        String expectedAmountOnField = "200";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='200']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.TextView[@text='200']")).click();
        Thread.sleep(500);
        String actualAmountOnField = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")).getText();
        Assert.assertEquals(expectedAmountOnField, actualAmountOnField); //To confirm that the amount displayed on the field is equal to the selected amount
    }
    @Test
    public void emptyAmountFieldTest() throws Exception {
        String expectedPageMessageAirtime = "What phone number would you like to recharge?"; //Message displayed on the Airtime Recharge introduction page
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='200']")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")).clear();
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        Thread.sleep(1000);
        String actualPageMessageAirtime = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_1")).getText();
        Assert.assertEquals(expectedPageMessageAirtime, actualPageMessageAirtime); //To verify that the page remains constant regardless if the next button click thus confirming disabled status
    }
    @Test
    public void invalidAmountLengthTest() throws Exception {
        String invalidAmountLength = "2";
        String expectedPageMessageAirtime = "What phone number would you like to recharge?"; //Message displayed on the Airtime Recharge introduction page
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")).sendKeys(invalidAmountLength);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        Thread.sleep(1000);
        String actualPageMessageAirtime = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_1")).getText();
        Assert.assertEquals(expectedPageMessageAirtime, actualPageMessageAirtime); //To verify that the page remains constant regardless if the next button click thus confirming disabled status
    }
    @Test
    public void validNumberValidAmountTest() throws Exception {
        String expectedPhoneNumber = "08123430728";     //Value of the saved phone number
        String expectedAmount = "200";          //Value of the saved amount
        String expectedServiceProvider = "Airtel";
        String securedPaymentValue = "Securely pay ₦ 200.00";
        String expectedConfirmationMessage = "You've just recharged 08123430728 with ₦200 airtime.";
        String validAmount = "200";
        String pin = "1234";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")).clear();
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_airtime_price")).sendKeys(validAmount);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.FrameLayout[@index='1']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_next")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/edit_text_phone_number")));
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.FrameLayout[@index='0']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_confirm_payment")).click();
        Thread.sleep(1000);
        String finalPhoneNumber = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_phone_number")).getText();
        String finalAmount = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/amount_view_amount")).getText();
        String finalDate = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_saved_date")).getText();
        String finalServiceProvider = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/text_view_mobile_network")).getText();
        String securelyPay = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_text_secure_pay")).getText(); //secured payment amount displayed on button
        Thread.sleep(500);
        Assert.assertEquals(expectedServiceProvider, finalServiceProvider);  //Comparing saved name displayed  with expected saved name
        Assert.assertEquals(expectedPhoneNumber, finalPhoneNumber); //Comparing Phone number displayed  with expected Phone number
        Assert.assertEquals(expectedAmount, finalAmount); //Comparing amount displayed  with expected actual amount
        Assert.assertEquals(securelyPay, securedPaymentValue); //Verifying the value on the securely pay button
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/button_text_secure_pay")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView[@index='1']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.ScrollView[@index='1']")).sendKeys(pin);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/successMessageText")));
        Thread.sleep(500);
        String actualConfirmationMessage = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/successMessageText")).getText();
        Assert.assertEquals(expectedConfirmationMessage, actualConfirmationMessage); //Comparing expected confirmation message with actual confirmation message
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/goHomeItem")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/okayButton")).click();

    }
}
