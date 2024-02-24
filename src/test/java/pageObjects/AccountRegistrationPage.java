package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjectBase.BasePage;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    WebElement txtLastName;

    @FindBy(id ="input-email")
    WebElement txtEMail;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPrivacy;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//img[@alt='Your Store']")
    WebElement imgOpencart;

    public void setFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setEMail(String EMail) {
        txtEMail.sendKeys(EMail);
    }

    public void setPassword(String Password) {
        txtPassword.sendKeys(Password);
    }

    public void setPrivacy() {
        chkPrivacy.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public String getConfirmationMsg(){
        try {
            return (msgConfirmation.getText());
        } catch (Exception e){
            return (e.getMessage());
        }
    }

    public void toHomePage(){
        imgOpencart.click();
    }


}
