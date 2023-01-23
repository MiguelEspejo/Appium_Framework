package Pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.*;

public class BasePage {
    static AppiumDriver driver;
    static WebElement datePickLocator;


    public BasePage(AppiumDriver driver) {
        BasePage.driver = driver;
    }

    public static void setCapabilities() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("appium:deviceName", "sdk_gphone_x86");
            capabilities.setCapability("appium:udid", "emulator-5554");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:platformVersion", "11");
            capabilities.setCapability("appium:appPackage", "com.booking");
            capabilities.setCapability("appium:appActivity", "com.booking.deeplink.decoder.TaxisDeeplinkActivityDecoder");
            driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void espera(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    private WebElement Find(String locator) {
        espera(1);
        return driver.findElement(By.id(locator));

    }

    private WebElement FindxPath(String locatorxPath) {
        espera(1);
        return driver.findElement(By.xpath(locatorxPath));

    }

    public void clickElement(String locator) {
        this.Find(locator).click();
    }

    public void controlTaps(String locAgregar, String locBtnRemover, String locValuesOcupantes, String locTapEdadNinos, String valorCuartoEsperado, String valorAdultosEsperado, String edadNinoEsperada){

        List<WebElement> botonesAgregar = driver.findElements(By.id(locAgregar));
        List<WebElement> valuesOcupantes = driver.findElements(By.id(locValuesOcupantes));
        // List<WebElement>botonesRemover = driver.findElements(By.id(locBtnRemover));

        int cantTapCuartos = Integer.parseInt(valorCuartoEsperado);
        int cantTapAdultos = Integer.parseInt(valorAdultosEsperado);
        int cantTapNinos = Integer.parseInt(edadNinoEsperada);
        int cantMaxEdadNinos = 17;

        String valorCuartoApp = valuesOcupantes.get(0).getText();
        String valorAdultosApp = valuesOcupantes.get(1).getText();

        //selecciono cuartos y adultos
        if (valorCuartoApp.equals(valorCuartoEsperado)) {
            System.out.println("El Valor del cuarto" + valorCuartoApp + "es igual al valor esperado en el test " + valorCuartoEsperado);
        } else {
            for (int i = 1; i < cantTapCuartos; i += 1) {
                botonesAgregar.get(0).click();
            }
        }

        espera(1);

        if (valorAdultosApp.equals(valorAdultosEsperado)) {
            System.out.println("El Valor de adultos " + valorAdultosApp + "es igual al valor esperado en el test " + valorAdultosEsperado);
        } else {
            //le resto 1 porque al agregar Rooms me agrega adultos a la vez, *Mejora*
            for (int i = 1; i < cantTapAdultos - 1; i += 1) {
                botonesAgregar.get(1).click();
            }
        }

        //selecciono los niños si se requiere seleccionar multiples niños se puede agregar
        espera(2);
        botonesAgregar.get(2).click();

        if (cantTapNinos > cantMaxEdadNinos) {
            System.out.println("No existe en la lista la cantidad edades mayores de 17 años" + edadNinoEsperada);
        } else {
            for (int i = 0; i <= cantTapNinos; i += 1) {
                espera(1);
                driver.findElement(By.xpath(locTapEdadNinos)).click();
            }
        }
    }


    public void write(String locator, String textoCaptura) {
        this.Find(locator).clear();
        this.Find(locator).sendKeys(new CharSequence[]{textoCaptura});
    }

    public void writexPath(String locatorxPath, String textoCaptura) {
        this.FindxPath(locatorxPath).clear();
        this.FindxPath(locatorxPath).sendKeys(new CharSequence[]{textoCaptura});
    }

    public void selectFechas(String fInicio, String fFin, String dateFickLocator, String locSelectFechas) {
        espera(1);
        datePickLocator = this.Find(dateFickLocator);
        verticalScroll(datePickLocator, 500);
        this.FindxPath(fInicio).click();
        this.FindxPath(fFin).click();
        this.Find(locSelectFechas).click();
    }

    public void selecResultadoBusqueda(String locResulBusqueda, String locScrollResulBusqueda, String cerrarMsj){
        espera(2);
        //cerrar msj ocacional
        //driver.findElement(By.id(cerrarMsj)).click();
        List<WebElement> resultadosBusqueda = driver.findElements(By.id(locResulBusqueda));
        espera(1);

        //verticalScroll(driver.findElement(By.className(locScrollResulBusqueda)),700);
        espera(2);
        resultadosBusqueda.get(1).click();
        //  driver.findElement(By.xpath(locResulBusqueda)).click();

    }


    //validaciones
    public String obtenerTextoDeElemento(String locator) {
        espera(2);
        return this.Find(locator).getText();
    }

    //scroll
    public static void verticalScroll(WebElement scrollLocator, int duration) {
        int centerX = scrollLocator.getRect().x + (scrollLocator.getSize().width / 8);
        double startY = scrollLocator.getRect().y + (scrollLocator.getSize().height * 0.9);
        double endY = scrollLocator.getRect().y + (scrollLocator.getSize().height * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), centerX, (int) startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), centerX, (int) endY));
        swipe.addAction(finger.createPointerUp(0));
        driver.perform(Arrays.asList(swipe));
    }


}
