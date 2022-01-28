package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass{

    @Test(dataProvider = "LoginData")
    public void loginDDT(String username, String password) throws InterruptedException, IOException{

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("User name is provided");
        lp.setPassword(password);
        logger.info("Password is provided");
        lp.clickLogin();

        Thread.sleep(3000);

        if(isAlertPresent()==true){

            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login Failed");
            captureScreen(driver,"loginDDT");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("Login Passed");
            Thread.sleep(3000);

            lp.clickLogout();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();

        }
    }

    public boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }
    @DataProvider(name="LoginData")
    Object[] getData() throws IOException
     {
        String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";

        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);

        String[][] logindata =new String[rownum][colcount];

        for(int i=1; i<=rownum; i++)
        {
            for(int j=0; j<colcount; j++)
            {
                logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return logindata;
    }
}
