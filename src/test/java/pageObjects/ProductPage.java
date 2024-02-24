package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjectBase.BasePage;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input-quantity']")
    WebElement txtQuality;

    @FindBy(xpath = "//*[@id='button-cart']")
    WebElement btnAddToCart;

    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    WebElement msgAddSuccess;

    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[4]/a/span")
    WebElement lnkShoppingCart;

    public void setQuality(String number) {
        txtQuality.clear();
        txtQuality.sendKeys(number);
    }

    public void addToCart() {
        btnAddToCart.click();
    }

    public boolean isAddSuccess(){
        return msgAddSuccess.isDisplayed();
    }

    public void toShoppingCartPage(){
        lnkShoppingCart.click();
    }
}
