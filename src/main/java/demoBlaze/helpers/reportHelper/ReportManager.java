package demoBlaze.helpers.reportHelper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class ReportManager {
    private static ExtentReports extentReport;
    private static String filePath = "";
    private static String reportName;

    private static final ConcurrentHashMap extentTestMap = new ConcurrentHashMap();
    private static ReportManager instance;

    private ReportManager() throws Exception {
        createExtentReportInstance();
    }

    public static ReportManager getInstance(){
        if (instance == null){
            //synchronized block to remove overhead
            synchronized (ReportManager.class){
                if (instance == null){
                    try {
                        // if instance is null, initialize
                        instance = new ReportManager();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    public ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) Thread.currentThread().getId());
    }

    public ExtentTest startTest(String testName) {
        ExtentTest test = extentReport.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public void flush() {
        extentReport.flush();
    }

    private void createExtentReportInstance() throws Exception {

        if (filePath.equals("")) {
            throw new Exception("You need to call Init method to create an ExtentReports Object");
        }

        createReportPath();
        extentReport = new ExtentReports();
        var htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setDocumentTitle("Automation Report " + reportName);
        htmlReporter.config().setReportName(reportName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");

        extentReport.attachReporter(htmlReporter);
    }

    public static void createReportPath() {
        new File(filePath).mkdirs();
    }

    public static void init(String reportPath, String reportName) throws Exception {
        if (extentReport == null) {
            filePath = reportPath;
            ReportManager.reportName = reportName;
        } else {
            throw new Exception("ExtentReports is already initialized");
        }
    }
}
