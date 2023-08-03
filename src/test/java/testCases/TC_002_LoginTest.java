package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void test_Login() {
        logger.info("**** Starting TC_002_LoginTest ****");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on My Account");
            homePage.clickLogin();
            logger.info("Clicked on Login link");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Providing logging details");
            loginPage.setEmail(resource.getString("email")); //valid email from config.properties
            loginPage.setPassword(resource.getString("password"));
            Thread.sleep(2000);
            loginPage.clickLogin();
            logger.info("Clicked on Login button");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetpage = myAccountPage.isMyAccountPagePresent();

            Assert.assertEquals(targetpage, true, "invalid login data");
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("**** Finished TC_002_LoginTest ****");
    }


}
