import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MaxPayLoginPositiveTest {

    public static void main(String[] args) {

        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); //optional, uncomment if not set in paths

        String userEmail = "whitebunnytest@gmail.com";
        String userPassword = "Bunny.test1000";
        String signInScreenURL = "https://my-sandbox.maxpay.com/#/signin";

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get(signInScreenURL);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-email")));

        WebElement userEmailTextField = driver.findElement(By.id("login-email"));
        WebElement userPasswordTextField = driver.findElement(By.id("login-password"));
        WebElement submitButton  = driver.findElement(By.xpath("/html/body/section/div//div/form[1]/div[5]/div/button"));

        System.out.println("Attempting to login with correct credentials:");
        System.out.println("Email: " + userEmail);
        System.out.println("Password: " + userPassword);


        userEmailTextField.sendKeys(userEmail);
        userPasswordTextField.sendKeys(userPassword);

        submitButton.submit();

        WebElement settingsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("setting")));

        settingsButton.click();

        WebElement accountName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-navbar\"]/ul[1]/li[4]/div/ul/li[1]/span")));

        try {//used because text arrives to the element considerably later
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(userEmail, accountName.getText());

        System.out.println("Successfully logged in to the system!");

        driver.quit();
    }
}
