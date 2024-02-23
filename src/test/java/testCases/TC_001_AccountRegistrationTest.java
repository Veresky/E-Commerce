package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testCaseBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    HomePage homePage;
    AccountRegistrationPage regPage;

    @Test(groups = {"regression","master"})
    public void verify_account_registration() {
        logger.info("*****  Starting  TC_001_AccountRegistrationTest    *****");
        logger.debug("application logs started...");

        try {
            homePage = new HomePage(driver);

            homePage.clickMyAccount();
            logger.info("Clicked on My Account link...");
            Thread.sleep(500);
            homePage.clickRegister();
            logger.info("Clicked on Registration link...");
            Thread.sleep(500);


            regPage = new AccountRegistrationPage(driver);

            logger.info("Entering customer information...");
            regPage.setFirstName(randomString().toUpperCase());
            regPage.setLastName(randomString().toUpperCase());
            regPage.setEMail(randomString()+"@gmail.com");
            String password = randomAlphaNumeric();
            regPage.setPassword(password);

            regPage.setPrivacy();
            Thread.sleep(500);

            regPage.clickContinue();
            logger.info("Clicked on Continue...");
            Thread.sleep(500);

            String confirmMsg = regPage.getConfirmationMsg();
            logger.info("Validating expected message...");
            if(confirmMsg.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }else {
                logger.error("Test failed...");
                Assert.fail();
            }

        } catch (Exception e) {
            logger.error("Test failed...");
            logger.debug("debug logs....");
            Assert.fail();
        }

        logger.debug("application logs ended...");
        logger.info("*****  Finished  TC_001_AccountRegistrationTest    *****");
    }
}
