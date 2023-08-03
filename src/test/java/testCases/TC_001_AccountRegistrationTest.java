package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    void test_account_Registration() {

        logger.info("*** Starting TC_001_AccountRegistrationTest ***");

        try {
            HomePage homePage = new HomePage(driver);
            AccountRegistrationPage registration = new AccountRegistrationPage(driver);

            homePage.clickMyAccount();
            logger.info("Clicked on My account link");
            homePage.clickRegister();
            logger.info("Clicked on registration link");

            registration.setFirstName(randomString().toUpperCase());
            registration.setLastName(randomString().toUpperCase());
            registration.setEmail(randomString() + "@gmail.com");
            String password = randomAlphaNumeric();
            registration.setPassword(password);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,200)");
            Thread.sleep(1000);

            registration.setPrivacyPolicy();
            registration.clickContinue();
            String confirmationMsg = registration.getConfirmationMsg();

            Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!", "Test failed");
        } catch (Exception e) {
            logger.error("test failed");
            Assert.fail();
        }
        logger.info("*** Finished TC_001_AccountRegistrationTest ***");
    }


}
