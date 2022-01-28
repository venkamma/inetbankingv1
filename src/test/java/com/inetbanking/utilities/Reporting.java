package com.inetbanking.utilities;

//Listener class used to generate extent report
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public void onStart(ITestContext testContext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timestamp + ".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/"+repName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Venkamma");

        htmlReporter.config().setDocumentTitle("InetBanking Test Project");//Title of the report
        htmlReporter.config().setReportName("Functional Automation Test Report");//Name of the report
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult res)
    {
            logger=extent.createTest(res.getName());
            logger.log(Status.PASS, MarkupHelper.createLabel(res.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult res)
    {
        logger=extent.createTest(res.getName());
        logger.log(Status.FAIL,MarkupHelper.createLabel(res.getName(),ExtentColor.RED));

        String screenshotpath = System.getProperty("user.dir")+"/Screenshots/"+res.getName()+".png";

        File f = new File(screenshotpath);

        if(f.exists())
        {
            try{
                logger.fail("Screenshot is below:"+ logger.addScreenCaptureFromPath(screenshotpath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult res)
    {
        logger=extent.createTest(res.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(res.getName(), ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }
}
