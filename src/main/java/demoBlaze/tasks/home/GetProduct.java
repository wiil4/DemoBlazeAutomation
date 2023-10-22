package demoBlaze.tasks.home;

import demoBlaze.actions.Label;
import demoBlaze.ui.home.HomeUI;
import org.openqa.selenium.WebDriver;

public class GetProduct {
    public static String price(WebDriver driver, String name){
        return Label.getText(driver, HomeUI.productPrice(name));
    }
}
