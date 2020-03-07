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


public class WalletTransactionFilter extends TestBase {

    @Test
    public void filterWalletTransaction() throws Exception {
        String amountSentToWallet = "₦1,000";
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/viewWalletTransactionsButton")));

        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/viewWalletTransactionsButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.lenddo.mobile.paylater.staging:id/title_menu_item_others")));
        Thread.sleep(500);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/title_menu_item_others")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/createdAtEditText")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/endAtEditText")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/progress")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@index='0']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("////android.widget.RelativeLayout[@index='0']")).click();
        String amountOnReceipt = driver.findElement(By.id("com.lenddo.mobile.paylater.staging:id/tvAmount")).getText();
        Assert.assertEquals(amountSentToWallet, amountOnReceipt); //To verify the latest value that was sent to the wallet (₦1,000)
    }

}
