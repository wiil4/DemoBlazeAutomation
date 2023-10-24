package demoBlaze.tasks.product;

import demoBlaze.actions.Label;
import demoBlaze.helpers.mathsHelper.NumberExtractor;
import demoBlaze.models.Product;
import demoBlaze.ui.ProductUI;
import org.openqa.selenium.WebDriver;

public class GetProduct {
    public static Product info(WebDriver driver){
        String productName = Label.getText(driver, ProductUI.productLabel);
        int productPrice = NumberExtractor.extract(Label.getText(driver, ProductUI.productPriceLabel));
        return new Product(productName, productPrice);
    }

}
