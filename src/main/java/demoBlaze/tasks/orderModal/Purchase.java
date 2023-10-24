package demoBlaze.tasks.orderModal;

import demoBlaze.actions.Click;
import demoBlaze.ui.OrderModalUI;
import org.openqa.selenium.WebDriver;

public class Purchase {
    public static void complete(WebDriver driver){
        Click.on(driver, OrderModalUI.purchaseButton);
    }
}
