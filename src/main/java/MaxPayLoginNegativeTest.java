import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MaxPayLoginNegativeTest {

    public static void main(String[] args) {

        String userEmail = "whitebunnytest@gmail.com";
        String userPassword = "wrong.password1000";
        String signInScreenURL = "https://my-sandbox.maxpay.com/#/signin";
        String systemIncorrectCredsMessage = "Некорректны пароль или email";

        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); //optional, uncomment if not set in paths
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get(signInScreenURL);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-email")));

        WebElement userEmailTextField = driver.findElement(By.id("login-email"));
        WebElement userPasswordTextField = driver.findElement(By.id("login-password"));
        WebElement submitButton  = driver.findElement(By.xpath("/html/body/section/div//div/form[1]/div[5]/div/button"));

        System.out.println("Attempting to login with incorrect credentials:");
        System.out.println("Email: " + userEmail);
        System.out.println("Password: " + userPassword);


        userEmailTextField.sendKeys(userEmail);
        userPasswordTextField.sendKeys(userPassword);

        submitButton.submit();

        WebElement incorrectCredsMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section/div//div/form[1]/div[3]/div/div/p")));

        try {//used because text arrives to the element considerably later
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(systemIncorrectCredsMessage, incorrectCredsMessage.getText());

        System.out.println("Success! Not logged in to the system with wrong password.");

        driver.quit();
    }
}
