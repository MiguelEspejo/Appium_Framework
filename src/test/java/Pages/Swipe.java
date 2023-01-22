package Pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class Swipe {


    public static void verticalScroll(AppiumDriver driver, WebElement locatorScroleable){

        int centerX = locatorScroleable.getRect().x + (locatorScroleable.getSize().width/4);
        double startY = locatorScroleable.getRect().y + (locatorScroleable.getSize().height * 0.9);
        double endY = locatorScroleable.getRect().y + (locatorScroleable.getSize().height * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),PointerInput.Origin.viewport(),centerX,(int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));

    }

}
