package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testCaseBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;

    @Test(groups = {"sanity","master"})
    public void verify_login(){
        logger.info("*****  Starting  TC_002_LoginTest    *****");
        logger.debug("capturing application debug logs....");

        try {
            //Actions on the Home page
            homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            //Actions on the Login page
            loginPage = new LoginPage(driver);
            logger.info("Entering email and password...");
            loginPage.setEMail(properties.getProperty("email"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLogin();

            Thread.sleep(2000);

            //Actions on My account page
            myAccountPage = new MyAccountPage(driver);
            boolean pageExists = myAccountPage.isMyAccountPageExists();
            Assert.assertEquals(pageExists,true,"Login failed");
        }catch (Exception e){
            Assert.fail("Error occurred: " + e.getMessage());
        }

        logger.info("*****  Finished  TC_002_LoginTest    *****");

    }
}
