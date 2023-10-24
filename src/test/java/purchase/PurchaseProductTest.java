package purchase;

import conf.BaseTest;
import demoBlaze.models.Product;
import demoBlaze.tasks.home.SaveProduct;
import demoBlaze.tasks.home.SelectCategory;
import demoBlaze.tasks.home.SelectProduct;
import demoBlaze.tasks.product.AddProduct;
import demoBlaze.tasks.product.GetProduct;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {
    private String categoryName = "Laptops";
    private String productName = "MacBook air";
    private int productPrice;

    @Test(description = "Verify product purchase by product name")
    public void purchaseProductByName(){

        SelectCategory.byName(driver, categoryName);
        productPrice = SaveProduct.price(driver,productName);
        SelectProduct.byName(driver, productName);
        Product chosenProduct = GetProduct.info(driver);
        Assert.assertEquals(chosenProduct.getName(), productName);
        Assert.assertEquals(chosenProduct.getPrice(), productPrice);
        AddProduct.toCart(driver);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
