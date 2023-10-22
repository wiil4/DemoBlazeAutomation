package purchase;

import conf.BaseTest;
import demoBlaze.tasks.home.GetProduct;
import demoBlaze.tasks.home.SelectCategory;
import demoBlaze.tasks.home.SelectProduct;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {

    @Test(description = "Verify product purchase by product name")
    public void purchaseProductByName(){
        String categoryName = "Laptops";
        String productName = "MacBook air";
        String price;
        SelectCategory.byName(driver, categoryName);
        price = GetProduct.price(driver,productName);
        SelectProduct.byName(driver, productName);
    }
}
