package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjectBase.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input-shipping-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//*[@id='input-shipping-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//*[@id='input-shipping-address-1']")
    WebElement txtAddress1;

    @FindBy(xpath = "//*[@id='input-shipping-city']")
    WebElement txtCity;

    @FindBy(xpath = "//*[@id='input-shipping-postcode']")
    WebElement txtPostCode;

    @FindBy(xpath = "//select[@name='country_id']")
    WebElement selectCountry;

//    @FindBy(xpath = "//option[@value='223']")
//    WebElement optionCountry;

    @FindBy(xpath = "//select[@name='zone_id']")
    WebElement selectRegion;

//    @FindBy(xpath = "//option[@value='3616']")
//    WebElement optionRegion;

    @FindBy(xpath = "//*[@id='button-shipping-address']")
    WebElement btnAddressCont;

    @FindBy(xpath = "//*[@id='button-shipping-methods']")
    WebElement btnShipMethod;

    @FindBy(xpath = "//*[@id='input-shipping-method-flat-flat']")
    WebElement rbtnShipOpt;

    @FindBy(xpath = "//button[@id='button-shipping-method']")
    WebElement btnShipCont;

    @FindBy(xpath = "//*[@id='button-payment-methods']")
    WebElement btnPayMethod;

    @FindBy(xpath = "//*[@id='input-payment-method-cod-cod']")
    WebElement rbtnPayOpt;

    @FindBy(xpath = "//button[@id='button-payment-method']")
    WebElement btnPayCont;

    @FindBy(xpath = "//*[@id='input-comment']")
    WebElement txtComment;

    @FindBy(xpath = "//*[@id='button-confirm']")
    WebElement btnCfmOrder;

    @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
    WebElement msgConfirmation;

    public void setFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setAddress1(String address1) {
        txtAddress1.sendKeys(address1);
    }

    public void setCity(String city) {
        txtCity.sendKeys(city);
    }

    public void setPostCode(String postCode) {
        txtPostCode.sendKeys(postCode);
    }

    public void selectCountry(String country) {
        Select optionCountry = new Select(selectCountry);
        optionCountry.selectByVisibleText(country);
    }

    public void selectRegion(String region) {
        Select optionRegion = new Select(selectRegion);
        optionRegion.selectByVisibleText(region);
    }

    public void cfmAddress(){
        btnAddressCont.click();
    }

    public void cfmShipMethod() throws InterruptedException {
        btnShipMethod.click();
        Thread.sleep(3000);
        rbtnShipOpt.click();
        Thread.sleep(3000);
        btnShipCont.click();
    }

    public void cfmPayMethod() throws InterruptedException {
        btnPayMethod.click();
        Thread.sleep(3000);
        rbtnPayOpt.click();
        Thread.sleep(3000);
        btnPayCont.click();
    }

    public void addComment(String comment){
        txtComment.sendKeys(comment);
    }

    public void confirmOrder(){
        btnCfmOrder.click();
    }

    public boolean isOrderSuccess(){
        return msgConfirmation.isDisplayed();
    }
}
