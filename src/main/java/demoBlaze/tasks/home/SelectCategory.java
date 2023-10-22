package demoBlaze.tasks.home;

import demoBlaze.actions.Click;
import demoBlaze.ui.home.HomeUI;
import org.openqa.selenium.WebDriver;

public class SelectCategory {
    public static void byName(WebDriver driver, String name){
        Click.on(driver, HomeUI.categoryItem(name));
    }
}
