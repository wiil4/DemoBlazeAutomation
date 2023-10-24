package demoBlaze.tasks.orderModal;

import demoBlaze.actions.IsDisplayed;
import demoBlaze.ui.OrderModalUI;
import org.openqa.selenium.WebDriver;

public class IsOrderModalDisplayed {
    public static Boolean inView(WebDriver driver){
        return IsDisplayed.element(driver, OrderModalUI.ordermodal);
    }
}
