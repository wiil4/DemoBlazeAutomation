package conf;

import demoBlaze.webDriver.factoryBrowser.FactoryBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = FactoryBrowser.getDriver("Firefox");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.demoblaze.com/");
    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
}
