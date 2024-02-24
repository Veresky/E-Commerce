package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testCaseBase.BaseClass;

public class TC_004_SearchProductTest extends BaseClass {

    HomePage homePage;

    SearchPage searchPage;

    @Test(groups = {"sanity","master"})
    public void verify_search_product() {
        logger.info("*****  Starting  TC_004_SearchProductTest    *****");
        logger.debug("capturing application debug logs....");

        try {
            //open home page and click search btn
            homePage = new HomePage(driver);
            homePage.toSearchPage();

            Thread.sleep(2000);

            //go to search page and search existed product
            String productName = properties.getProperty("productName");
            searchPage = new SearchPage(driver);
            searchPage.searchKeywords(productName);

            Thread.sleep(2000);

            //check if search success
            boolean result = searchPage.isProductExist(productName);
            Assert.assertEquals(result, true,"Search success");
        }catch (Exception e){
            Assert.fail("Error occurred: " + e.getMessage());
        }

        logger.info("*****  Finished  TC_004_SearchProductTest    *****");

    }

}
