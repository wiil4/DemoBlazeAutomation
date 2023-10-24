package demoBlaze.tasks.cart;

import demoBlaze.actions.Label;
import demoBlaze.helpers.mathsHelper.NumberExtractor;
import demoBlaze.models.Product;
import demoBlaze.ui.CartUI;
import org.openqa.selenium.WebDriver;

public class ObtainProduct {
    public static Product info(WebDriver driver){
        String name = Label.getText(driver, CartUI.productNameLabel);
        int price = NumberExtractor.extract(Label.getText(driver, CartUI.productPriceLabel));
        return new Product(name, price);
    }
}
