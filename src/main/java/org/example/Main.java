package org.example;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static AppiumDriver driver;
    static WebElement datePick;
    public static void main(String[] args) {

        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("appium:deviceName","sdk_gphone_x86");
            capabilities.setCapability("appium:udid","emulator-5554");
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("appium:platformVersion", "11");
            capabilities.setCapability("appium:appPackage","com.booking");
            capabilities.setCapability("appium:appActivity","com.booking.deeplink.decoder.TaxisDeeplinkActivityDecoder");
            //capabilities.setCapability("noReset","true");
            //capabilities.setCapability("appWaitActivity","com.booking.login.LoginActivity");
            //capabilities.setCapability("","");appWaitActivity or appWaitPackage
            //capabilities.setCapability("disableWindowAnimation","true");
            //capabilities.setCapability("ignoreHiddenApiPolicyError","true");

            driver = new AppiumDriver (new URL ("http://localhost:4723/wd/hub"),capabilities);

            System.out.println("*Inicialización de app completa*");

            driver.findElement(By.id("com.booking:id/facet_search_box_accommodation_destination")).click();
            driver.findElement(By.id("com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content")).sendKeys("Cusco");
            driver.findElement(By.id("com.booking:id/view_disambiguation_destination_title")).click(); //le da clic al primero aunque existan varios
            Thread.sleep(3000);


            //select range of date
            datePick = driver.findElement(By.id("com.booking:id/calendar_month_list"));
            verticalScroll(driver,datePick); //investigar bien como se usa el metodo para hacer scroll


            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.view.View[@content-desc=\"14 February 2023\"]")).click();

            driver.findElement(By.xpath("//android.view.View[@content-desc=\"28 February 2023\"]")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("com.booking:id/facet_date_picker_confirm")).click();

            //select rooms ad guest
            driver.findElement(By.id("com.booking:id/facet_search_box_accommodation_occupancy")).click();
            Thread.sleep(1000);

            List<WebElement>addButons = driver.findElements(By.id("com.booking:id/bui_input_stepper_add_button"));
            List<WebElement>removeButons = driver.findElements(By.id("com.booking:id/bui_input_stepper_remove_button"));
            List<WebElement>valuesRG = driver.findElements(By.id("com.booking:id/bui_input_stepper_value"));

            //Age child
            addButons.get(2).click(); //abro la pantalla de childs porque ya tengo el select en la lista
           // driver.findElement(By.id("android:id/numberpicker_input")).sendKeys(Keys.ARROW_DOWN);

            datePick = driver.findElement(By.className("android.widget.NumberPicker"));
            verticalScroll(driver,datePick); //investigar bien como se usa el metodo para hacer scroll
            verticalScroll(driver,datePick);
            verticalScroll(driver,datePick);
            driver.findElement(By.id("android:id/button1")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("com.booking:id/group_config_apply_button")).click();
            Thread.sleep(1000);

            //search
            driver.findElement(By.id("com.booking:id/facet_search_box_cta")).click();
            Thread.sleep(3000);

            List<WebElement> resultSearch = driver.findElements(By.id("com.booking:id/bui_review_score_view"));
            resultSearch.get(1).click();
            Thread.sleep(2000);

            //fill your info.
            //select room
            driver.findElement(By.id("com.booking:id/select_room_cta")).click();
            Thread.sleep(2000);
            //serverve these option
            driver.findElement(By.id("com.booking:id/recommended_block_reserve_button")).click();
            Thread.sleep(2000);
           // driver.findElement(By.id("com.booking:id/main_action")).click();
            Thread.sleep(000);
            driver.findElement(By.id("com.booking:id/bstage1_contact_firstname_value")).click();
            Thread.sleep(4000);

            driver.findElement(By.xpath("//android.widget.TextView[@text='First Name']"));

        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public static void verticalScroll(AppiumDriver driver, WebElement locator){
        //investigar bien como se usa el metodo para hacer scroll fuente:
        //https://www.youtube.com/watch?v=XAmR_8T3HIw&ab_channel=AutomationWithArnab


        int centerX = datePick.getRect().x + (datePick.getSize().width/4);
        double startY = datePick.getRect().y + (datePick.getSize().height * 0.9);
        double endY = datePick.getRect().y + (datePick.getSize().height * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),PointerInput.Origin.viewport(),centerX,(int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));


    }
}