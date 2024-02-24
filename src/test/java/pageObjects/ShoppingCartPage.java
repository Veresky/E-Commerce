package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjectBase.BasePage;

public class ShoppingCartPage extends BasePage {

    public  ShoppingCartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btnCheckout;

    @FindBy(xpath="/html/body/main/div[2]/div/div/div[1]/div/table/tfoot/tr[4]/td[2]")
    WebElement lblTotalPrice;

    public void toCheckoutPage(){
        btnCheckout.click();
    }

    public String getTotalPrice() {
        return lblTotalPrice.getText();
    }
}
