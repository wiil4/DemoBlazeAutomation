package demoBlaze.tasks.purchaseDoneModal;

import demoBlaze.actions.IsDisplayed;
import demoBlaze.ui.PurchaseDoneUI;
import org.openqa.selenium.WebDriver;

public class IsPurchaseDoneDisplayed {
    public static Boolean inView(WebDriver driver){
        return IsDisplayed.element(driver, PurchaseDoneUI.purchaseDoneModal);
    }
}
