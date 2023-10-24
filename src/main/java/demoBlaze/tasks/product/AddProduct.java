package demoBlaze.tasks.product;

import demoBlaze.actions.Alert;
import demoBlaze.actions.Click;
import demoBlaze.ui.ProductUI;
import org.openqa.selenium.WebDriver;

public class AddProduct {
    public static void toCart(WebDriver driver){
        Click.on(driver, ProductUI.addCartButton);
        Alert.isPresent(driver);
        Alert.accept(driver);
    }

}
