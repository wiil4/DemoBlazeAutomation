package purchase;

import conf.BaseTest;
import demoBlaze.models.Product;
import demoBlaze.models.PurchaseData;
import demoBlaze.tasks.cart.GetOrder;
import demoBlaze.tasks.cart.Order;
import demoBlaze.tasks.home.SaveProduct;
import demoBlaze.tasks.home.SelectCategory;
import demoBlaze.tasks.home.SelectProduct;
import demoBlaze.tasks.navBar.SelectNav;
import demoBlaze.tasks.orderModal.FillOrder;
import demoBlaze.tasks.orderModal.IsOrderModalDisplayed;
import demoBlaze.tasks.orderModal.Purchase;
import demoBlaze.tasks.product.AddProduct;
import demoBlaze.tasks.product.GetProduct;
import demoBlaze.tasks.purchaseDoneModal.AcceptPurchase;
import demoBlaze.tasks.purchaseDoneModal.GetPurchase;
import demoBlaze.tasks.purchaseDoneModal.GetThankPurchase;
import demoBlaze.tasks.purchaseDoneModal.IsPurchaseDoneDisplayed;
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

        Product productInCar = GetOrder.info(driver);
        Assert.assertEquals(productInCar.getName(), productName);
        Assert.assertEquals(productInCar.getPrice(), selectedProductPrice);
        Order.place(driver);

        String orderName = "Wil1234";
        Assert.assertTrue(IsOrderModalDisplayed.inView(driver));
        FillOrder.withData(driver, orderName, "123","123","123","123","123");
        Purchase.complete(driver);

        Assert.assertTrue(IsPurchaseDoneDisplayed.inView(driver));
        Assert.assertEquals(GetThankPurchase.text(driver), "Thank you for your purchase!" );

        PurchaseData purchaseData = GetPurchase.info(driver);
        Assert.assertEquals(purchaseData.getName(), orderName);
        Assert.assertEquals(purchaseData.getAmount(), selectedProductPrice);

        AcceptPurchase.done(driver);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
