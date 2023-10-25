package demoBlaze.tasks.home;

import demoBlaze.actions.Label;
import demoBlaze.actions.WaitUntilElement;
import demoBlaze.ui.HomeUI;
import org.openqa.selenium.WebDriver;

public class GetLoggedUser {
    public static String usernameText(WebDriver driver){
        return Label.getText(driver, HomeUI.loggedUserLabel);
    }
}
