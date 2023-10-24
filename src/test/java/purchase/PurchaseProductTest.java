package purchase;

import conf.BaseTest;
import demoBlaze.helpers.jsonDataHelper.JsonTestDataHelper;
import demoBlaze.models.Product;
import demoBlaze.models.testData.ProductSelection;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class PurchaseProductTest extends BaseTest {

    public static String loginTestDataPath = "resources/testdata/purchase/";

    @Test(description = "Verify product purchase by product name", dataProvider = "ProductPurchaseDataProvider")
    public void purchaseProductByName(ProductSelection product){

        SelectCategory.byName(driver, product.getCategory());
        int selectedProductPrice = SaveProduct.price(driver,product.getName());
        SelectProduct.byName(driver, product.getName());
        Product chosenProduct = GetProduct.info(driver);

        Assert.assertEquals(chosenProduct.getName(), product.getName());
        Assert.assertEquals(chosenProduct.getPrice(), selectedProductPrice);
        AddProduct.toCart(driver);
        SelectNav.cart(driver);

        Product productInCar = GetOrder.info(driver);
        Assert.assertEquals(productInCar.getName(), product.getName());
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
    }

    @DataProvider(name="ProductPurchaseDataProvider")
    public Object[] productPurchaseDP() throws FileNotFoundException {
        return JsonTestDataHelper.getInstance().geTestData(loginTestDataPath + "purchaseProduct.json", ProductSelection.class);
    }
}
