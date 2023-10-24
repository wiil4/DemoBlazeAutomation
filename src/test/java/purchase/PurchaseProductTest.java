package purchase;

import conf.BaseTest;
import demoBlaze.models.Product;
import demoBlaze.tasks.cart.ObtainProduct;
import demoBlaze.tasks.cart.Order;
import demoBlaze.tasks.home.SaveProduct;
import demoBlaze.tasks.home.SelectCategory;
import demoBlaze.tasks.home.SelectProduct;
import demoBlaze.tasks.navBar.SelectNav;
import demoBlaze.tasks.product.AddProduct;
import demoBlaze.tasks.product.GetProduct;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {
    private String categoryName = "Laptops";
    private String productName = "MacBook air";

    @Test(description = "Verify product purchase by product name")
    public void purchaseProductByName(){

        SelectCategory.byName(driver, categoryName);
        int selectedProductPrice = SaveProduct.price(driver,productName);
        SelectProduct.byName(driver, productName);
        Product chosenProduct = GetProduct.info(driver);

        Assert.assertEquals(chosenProduct.getName(), productName);
        Assert.assertEquals(chosenProduct.getPrice(), selectedProductPrice);
        AddProduct.toCart(driver);
        SelectNav.cart(driver);

        Product productInCar = ObtainProduct.info(driver);
        Assert.assertEquals(productInCar.getName(), productName);
        Assert.assertEquals(productInCar.getPrice(), selectedProductPrice);
        Order.place(driver);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
