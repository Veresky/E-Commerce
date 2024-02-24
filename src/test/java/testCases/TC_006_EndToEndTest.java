package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import testCaseBase.BaseClass;

public class TC_006_EndToEndTest extends BaseClass {

    HomePage homePage;

    AccountRegistrationPage regPage;

    SearchPage searchPage;

    ProductPage productPage;

    ShoppingCartPage cartPage;

    CheckoutPage checkoutPage;

    @Test(groups = {"master"})
    public void verify_end_to_end_test(){
        logger.info("*****  Starting  TC_006_EndToEndTest    *****");
        logger.debug("application logs started...");

        SoftAssert softAssert = new SoftAssert();

        try {
            //1.new user registration
            Thread.sleep(3000);
            logger.info("new user registration...");
            homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickRegister();
            regPage = new AccountRegistrationPage(driver);
            String firstName = randomString().toUpperCase();
            String lastName = randomString().toUpperCase();
            regPage.setFirstName(firstName);
            regPage.setLastName(lastName);
            regPage.setEMail(randomString() + "@gmail.com");
            regPage.setPassword(randomAlphaNumeric());
            regPage.setPrivacy();
            regPage.clickContinue();
            Thread.sleep(3000);
            String confirmationMsg = regPage.getConfirmationMsg();
            System.out.println(confirmationMsg);
            softAssert.assertEquals(confirmationMsg, "Your Account Has Been Created!");     //validation
            regPage.toHomePage();

            //2.search product
            Thread.sleep(3000);
            logger.info("search product...");
            homePage.toSearchPage();
            searchPage = new SearchPage(driver);
            searchPage.searchKeywords(properties.getProperty("productName"));

            //3.add product to shopping cart
            Thread.sleep(3000);
            logger.info("add product to shopping cart...");
            searchPage.toProductPage();
            productPage = new ProductPage(driver);
            productPage.setQuality("3");
            productPage.addToCart();
            Thread.sleep(6000);
            productPage.toShoppingCartPage();

            //4.check shopping cart
            Thread.sleep(3000);
            logger.info("check shopping cart...");
            cartPage = new ShoppingCartPage(driver);
            String totalPrice = cartPage.getTotalPrice();
            Thread.sleep(3000);
            System.out.println("The total price is: " + totalPrice);
            softAssert.assertEquals(totalPrice,"$369.60");  //validation
            cartPage.toCheckoutPage();

            //5.checkout
            Thread.sleep(3000);
            logger.info("checkout...");
            checkoutPage = new CheckoutPage(driver);
            checkoutPage.setFirstName(firstName);
            checkoutPage.setLastName(lastName);
            checkoutPage.setAddress1(randomString());
            checkoutPage.setCity(randomString());
            checkoutPage.setPostCode(randomString());
            Thread.sleep(3000);
            checkoutPage.selectCountry(properties.getProperty("country"));
            Thread.sleep(3000);
            checkoutPage.selectRegion(properties.getProperty("region"));
            Thread.sleep(3000);
            checkoutPage.cfmAddress();
            Thread.sleep(3000);
            checkoutPage.cfmShipMethod();
            Thread.sleep(3000);
            checkoutPage.cfmPayMethod();
            Thread.sleep(3000);
            checkoutPage.addComment(randomString());
            Thread.sleep(3000);
            checkoutPage.confirmOrder();
            Thread.sleep(3000);
            boolean result = checkoutPage.isOrderSuccess();
            softAssert.assertEquals(result,true);    //validation

            softAssert.assertAll("Test success");

        }catch (Exception e){
            Assert.fail("Test failed");
        }

        logger.info("*****  Finished  TC_006_EndToEndTest    *****");
    }

}
