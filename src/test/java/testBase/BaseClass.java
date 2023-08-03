package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {
    public static WebDriver driver;

    public Logger logger;

    public ResourceBundle resource;

    @BeforeClass(groups = {"Master", "Sanity", "Regression"})
    @Parameters("browser")
    public void setup(String bsr) {

        resource = ResourceBundle.getBundle("config"); //load config.properties file
        logger = LogManager.getLogger(this.getClass());

//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});

        if (bsr.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (bsr.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(resource.getString("appURL1"));
        driver.manage().window().maximize();
    }


    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber() {
        String generatedString2 = RandomStringUtils.randomNumeric(10);
        return generatedString2;
    }

    public String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);
        return (str + "@" + num);
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }

    @AfterClass(groups = {"Master", "Sanity", "Regression"})
    @Parameters("browser")
    public void tearDown() {
        driver.quit();
    }


}
