package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginTest() throws IOException {
        logger.info("URL is opened");

        LoginPage login = new LoginPage(driver);
        login.setUserName(username);
        logger.info("Username Entered");

        login.setPassword(password);
        logger.info("Password Entered");
        login.clickLogin();
        if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Login Test case passed");
        }
        else
        {
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Login Test case failed");
        }
    }
}
