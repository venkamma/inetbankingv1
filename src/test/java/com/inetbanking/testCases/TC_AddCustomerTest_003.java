package com.inetbanking.testCases;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class TC_AddCustomerTest_003 extends BaseClass {

    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        lp.setPassword(password);
        lp.clickLogin();

        Thread.sleep(3000);

        AddCustomerPage cp = new AddCustomerPage(driver);

        cp.clickCustomerLink();
        logger.info("Providing Customer Information");

        cp.custName("Sana");
        cp.custGender("female");
        cp.custDOB("06", "22", "2013");
        Thread.sleep(3000);
        cp.custAddress("6805 S Mariposa Ln");
        cp.custCity("Dublin");
        cp.custState("California");
        cp.custPin("945684");
        cp.custPhone("5104616080");

        String email = randomestring() + "@gmail.com";
        cp.custEmail(email);
        cp.custPassword("abc123");
        Thread.sleep(3000);
        cp.clickCustSubmit();

        Thread.sleep(3000);

        logger.info("Valdiation Started");

        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

        if (res == true) {
            Assert.assertTrue(true);
            logger.info("Test Case Passed");
        } else {
            logger.info("Test Case Failed");
            captureScreen(driver, "addNewCustomer()");
            Assert.assertTrue(false);
        }
    }
}
