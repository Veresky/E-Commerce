package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testCaseBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
    public void verify_loginDDT(String email, String password, String expRes){
        logger.info("*****  Starting  TC_003_LoginLoginDDT    *****");
        logger.debug("capturing application debug logs....");

        try {
            //Actions on the Home page
            homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            //Actions on the Login page
            loginPage = new LoginPage(driver);
            logger.info("Entering email and password...");
            loginPage.setEMail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin(); //login button

            Thread.sleep(2000);

            //Actions on the My account page
            myAccountPage = new MyAccountPage(driver);
            boolean pageExists = myAccountPage.isMyAccountPageExists();

            //validation
            if(expRes.equalsIgnoreCase("Valid")){
                if(pageExists){
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);

                }else {
                    Assert.fail();  //equivalent to Assert.assertTrue(false);
                }
            }

            if(expRes.equalsIgnoreCase("Invalid")){
                if(pageExists){
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                }else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("*****  Finished  TC_003_LoginLoginDDT    *****");
    }
}
