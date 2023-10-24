package demoBlaze.tasks.orderModal;

import demoBlaze.actions.Enter;
import demoBlaze.ui.OrderModalUI;
import org.openqa.selenium.WebDriver;

public class FillOrder {
    public static void withData(WebDriver driver, String name, String country, String city, String creditCard, String month, String year){
        Enter.text(driver, OrderModalUI.nameInput, name);
        Enter.text(driver, OrderModalUI.countryInput, country);
        Enter.text(driver, OrderModalUI.cityInput, city);
        Enter.text(driver, OrderModalUI.cardInput, creditCard);
        Enter.text(driver, OrderModalUI.monthInput, month);
        Enter.text(driver, OrderModalUI.yearInput, year);
    }
}
