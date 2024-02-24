package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import testCaseBase.BaseClass;

import java.util.logging.Logger;

public class TC_005_AddItemToCartTest extends BaseClass {

    HomePage homePage;

    SearchPage searchPage;

    ProductPage productPage;

    @Test(groups = {"sanity","master"})
    public void verify_add_item_to_cart(){
        logger.info("*****  Starting  TC_005_AddItemToCartTest    *****");
        logger.debug("capturing application debug logs....");

        try {
            //open home page
            homePage = new HomePage(driver);
            homePage.toSearchPage();

            Thread.sleep(2000);

            //go to search page
            searchPage = new SearchPage(driver);
            String productName = properties.getProperty("productName");
            searchPage.searchKeywords(productName);

            //go to product page
            Thread.sleep(5000);
            searchPage.toProductPage();

            productPage = new ProductPage(driver);
            productPage.setQuality("3");
            productPage.addToCart();

            Thread.sleep(2000);

            //determine if success
            boolean result = productPage.isAddSuccess();
            Assert.assertEquals(result,true,"Add item to cart success");
        }catch (Exception e){
            Assert.fail("Add item to cart fail");
        }

        logger.info("*****  Finished  TC_005_AddItemToCartTest    *****");

    }

}
