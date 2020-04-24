package br.com.automation.Utils;

import com.relevantcodes.extentreports.*;

import java.io.*;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true, NetworkMode.ONLINE);
            extent.loadConfig(new File("/src/test/resources/ExtentConfig.xml"));
        }
        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        return extent;
    }

    public synchronized static void closeReporter() {
        if(extent!=null){
            extent.flush();
            //extent.close();
        }
    }
}