package demoBlaze.tasks.product;

import demoBlaze.helpers.mathsHelper.NumberExtractor;
import demoBlaze.models.Product;
import demoBlaze.ui.ProductUI;
import org.openqa.selenium.WebDriver;

public class GetProduct {
    public static Product info(WebDriver driver){
        String productName = driver.findElement(ProductUI.productLabel).getText();
        int productPrice = NumberExtractor.extract(driver.findElement(ProductUI.productPriceLabel).getText());
        return new Product(productName, productPrice);
    }

}
