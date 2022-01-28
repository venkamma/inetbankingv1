package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    WebDriver ldriver;

    public AddCustomerPage(WebDriver driver){

        ldriver = driver;
        PageFactory.initElements(ldriver,this);
    }

    @FindBy(linkText = "New Customer")
    WebElement linkAddNewCustomer;
    @FindBy(name="name")
    WebElement txtCustomerName;
    @FindBy(name="rad1")
    WebElement rdgender;
    @FindBy(name="dob")
    WebElement txtdob;
    @FindBy(name="addr")
    WebElement txtaddr;
    @FindBy(name="city")
    WebElement txtcity;
    @FindBy(name="state")
    WebElement txtstate;
    @FindBy(name="pinno")
    WebElement txtpin;
    @FindBy(name="telephoneno")
    WebElement txtphone;
    @FindBy(name="emailid")
    WebElement txtemailid;
    @FindBy(name="password")
    WebElement txtpassword;
    @FindBy(name="sub")
    WebElement btnSubmit;

    public void clickCustomerLink(){
        linkAddNewCustomer.click();
    }
    public void custName(String cname){
        txtCustomerName.sendKeys(cname);
    }
    public void custGender(String cgender){
        rdgender.click();
    }
    public void custDOB(String mm, String dd, String yy){
        txtdob.sendKeys(mm);
        txtdob.sendKeys(dd);
        txtdob.sendKeys(yy);
    }
    public void custAddress(String caddress){
        txtaddr.sendKeys(caddress);
    }
    public void custCity(String ccity){
        txtcity.sendKeys(ccity);
    }
    public void custState(String cstate){
        txtstate.sendKeys(cstate);
    }
    public void custPin(String cpin){
        txtpin.sendKeys(String.valueOf(cpin));
    }
    public void custPhone(String cphone){
        txtphone.sendKeys(cphone);
    }
    public void custEmail(String cemail){
        txtemailid.sendKeys(cemail);
    }
    public void custPassword(String cpwd){
        txtpassword.sendKeys(cpwd);
    }
    public void clickCustSubmit(){
        btnSubmit.click();
    }
}
