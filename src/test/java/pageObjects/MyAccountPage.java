package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjectBase.BasePage;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(.,'My Account')]")
    WebElement msgHeading;

    @FindBy(xpath = "(//a[contains(text(),'Logout')])[2]")
    WebElement lnkLogout;

    @FindBy(linkText = "Login") //Login link added in step5
    WebElement lnkLogin;

    public boolean isMyAccountPageExists(){
        try {
            boolean result = msgHeading.isDisplayed();
            return result;
        }catch (Exception e){
            return false;
        }
    }

    public void clickLogout(){
        lnkLogout.click();
    }

}
