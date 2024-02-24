package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjectBase.BasePage;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input-search']")
    WebElement txtSearchKeywords;

    @FindBy(xpath = "//*[@id='button-search']")
    WebElement btnSearchKeywords;

    @FindBy(xpath="/html/body/main/div[2]/div/div/div[5]/div/div/div[1]/a/img")
    List<WebElement> searchProducts;

    public void searchKeywords(String productName){
        txtSearchKeywords.sendKeys(productName);
        btnSearchKeywords.click();
    }

    public boolean isProductExist(String productName){
        boolean flag = false;
        for(WebElement product : searchProducts){
            if (product.getAttribute("title").equals(productName)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void toProductPage(){
        for (WebElement product : searchProducts){
            product.click();
            break;
        }
    }

}
