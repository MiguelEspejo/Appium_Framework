package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class AlojamientoPage extends BasePage {


    private String InputDestino = "com.booking:id/facet_search_box_accommodation_destination";
    private String InputDestinoCaptura = "com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content";
    private String primerResultadoDestinos = "com.booking:id/view_disambiguation_destination_title";
    private String locFechaInicio = "//android.view.View[@content-desc=\"14 February 2023\"]";
    private String locFechaFin = "//android.view.View[@content-desc=\"28 February 2023\"]";
    private String locDatePick = "com.booking:id/calendar_month_list";

    private String locSelect = "com.booking:id/facet_date_picker_confirm";
    private String locOcupantes = "com.booking:id/facet_search_box_accommodation_occupancy";
    private String locBotonAgregar = "com.booking:id/bui_input_stepper_add_button";
    private String locValueOcupantes = "com.booking:id/bui_input_stepper_value";
    private String locBotonRemover = "com.booking:id/bui_input_stepper_remove_button";
    private String locTapEdadNino = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.NumberPicker/android.widget.Button[2]";
    private String locBtnAplicar = "com.booking:id/group_config_apply_button";
    private String locBtnOkEdades = "android:id/button1";
    private String locBtnBusqueda = "com.booking:id/facet_search_box_cta";
    private String locSelResulBusq = "com.booking:id/bui_review_score_view";
    private String cerrarMsj = "com.booking:id/bui_banner_close_button";
    private String locScrollResulBusqueda = "androidx.recyclerview.widget.RecyclerView";
    private String locBtnSelectRooms = "com.booking:id/facet_price_view";
    private String locScrollRooms = "com.booking:id/rooms_recycler_view";

    private String locBtnSelectRoom = "com.booking:id/rooms_item_select_text_view";
    private String locBtnReservar = "com.booking:id/main_action";
    private String locInpNombreHuesp = "com.booking:id/bui_input_container_content";
    private String locScrollInput = "com.booking:id/content_recycler_view";
    private String locClicFirstName = "//android.widget.TextView[@text='First Name *']";
    private String locAlertDoesntCerrar = "com.booking:id/button_positive";
    private String locBtnAgragarDetalles = "com.booking:id/action_button";
    //validar
    private String locTituloHospedaje = "com.booking:id/bp_overview_hotelname";
    private String locFinalStep = "com.booking:id/action_button";
    private String locNumeroTarj = "com.booking:id/new_credit_card_number_edit";
    private String locTipoTarjeta = "com.booking:id/new_credit_card_type_spinner";
    private String locFechaExpTarj = "com.booking:id/new_credit_card_expiry_date_edit";
    private String locNombreUsrTarjeta = "com.booking:id/new_credit_card_holder_edit";
    private String locCheckin = "com.booking:id/checkin_date";
    private String locCheckOut = "com.booking:id/checkout_date";
    private String locTotalvalue = "com.booking:id/title";
    private String locFechasSelec = "com.booking:id/facet_search_box_basic_field_label";
    private String locRoomsTotalImp = "com.booking:id/price_view_price";
    private String locAlertNoDestino = "com.booking:id/message";
    private String locBtnOkAlertNoDestino = "com.booking:id/message";


    //variables
    private String fechasSelectionadas;
    private String importeValueRooms;

    public AlojamientoPage() {
        super(driver);
    }

    public void abrirAplicacionBooking() {
        BasePage.setCapabilities();
    }

    public void clicEnInputDestino() {
        this.clickElement(this.InputDestino);
        espera(2);
    }

    public void capturaDestino(String destino) {
        this.write(this.InputDestinoCaptura, destino);
    }

    public void clicEnResultadoDestino() {
        this.clickElement(this.primerResultadoDestinos);
    }

    public void seleccionarFechasAlojamiento() {
        this.selectFechas(this.locFechaInicio, this.locFechaFin, this.locDatePick, this.locSelect);

    }

    public void seleccionarOcupantes(String cantCuartos, String cantAdultos, String edadNino) {
        this.clickElement(this.locOcupantes);
        this.controlTaps(this.locBotonAgregar, this.locBotonRemover, this.locValueOcupantes, this.locTapEdadNino, cantCuartos, cantAdultos, edadNino);
        this.clickElement(this.locBtnOkEdades);
        espera(1);
        this.clickElement(this.locBtnAplicar);
    }

    public void selBtnSearch() {
        //obteniendo fechas para validación
        List<WebElement> elementsPrincipales = driver.findElements(By.id(locFechasSelec));
        fechasSelectionadas = elementsPrincipales.get(1).getText();

        this.clickElement(this.locBtnBusqueda);
    }

    public void selResultadoSearch() {
        this.selecResultadoBusqueda(this.locSelResulBusq, this.locScrollResulBusqueda, this.cerrarMsj);
    }

    public void selecionaHabitacion() {
        espera(1);
        this.clickElement(this.locBtnSelectRooms);
        importeValueRooms = this.obtenerTextoDeElemento(locRoomsTotalImp);
        espera(1);
        //aggregar scroll
        verticalScroll(driver.findElement(By.id(locScrollRooms)), 1000);
        this.clickElement(this.locBtnSelectRoom);
        espera(2);
        this.clickElement(this.locBtnReservar);
    }

    public void capturaDatosHuesp(String nombre, String apellido, String email, String direccion, String codigoPostal, String ciudad, String pais, String telefono) {
        espera(5);

        //driver.switchTo().alert().dismiss();

        //driver.findElement(By.id(locAlertDoesntCerrar)).click();
        driver.findElement(By.xpath(locClicFirstName)).click();
        List<WebElement> inputs = driver.findElements(By.id(locInpNombreHuesp));
        espera(2);
        inputs.get(0).sendKeys(nombre);
        espera(2);
        inputs.get(1).sendKeys(apellido);
        espera(2);
        inputs.get(2).sendKeys(email);

        WebElement locScrollCapture = driver.findElement(By.id(locScrollInput));
        verticalScroll(locScrollCapture, 500);
        List<WebElement> inputs2 = driver.findElements(By.id(locInpNombreHuesp));
        espera(2);

        inputs2.get(0).sendKeys(direccion);
        espera(2);
        inputs2.get(1).sendKeys(codigoPostal);
        espera(2);
        inputs2.get(2).sendKeys(ciudad);
        espera(2);
        inputs2.get(3).clear();
        espera(2);
        inputs2.get(3).sendKeys(pais);
        espera(2);
        inputs2.get(4).sendKeys(telefono);
        espera(2);
        this.clickElement(locBtnAgragarDetalles);
        espera(2);
    }

    public String validarTotal() {
        return this.obtenerTextoDeElemento(this.locTotalvalue);
    }

    public void verificarAlojamiento() {
        this.clickElement(locFinalStep);
        //capturar datos de tarjeta
        this.write(locNumeroTarj, "4555 7887 6544 3333");
        //  this.write(locTipoTarjeta,"Visa");
        this.write(locFechaExpTarj, "02/25");

        //validaciones
        System.out.println("Que trae el locator nombre del final step " + this.obtenerTextoDeElemento(this.locNombreUsrTarjeta));
        Assert.assertEquals("El nombre del usuario de la tarjeta no es el que se encuentra capturado ", "Roberto Lopez", this.obtenerTextoDeElemento(this.locNombreUsrTarjeta));
        verticalScroll(driver.findElement(By.id(locScrollInput)), 700);
        String checkInEsperado = this.obtenerTextoDeElemento(this.locCheckin);
        String checkOutEsperado = this.obtenerTextoDeElemento(this.locCheckOut);
        Assert.assertEquals("La fecha de checkin es diferente a la que se reservara ", "Tue Feb 14 2023", checkInEsperado);
        Assert.assertEquals("La fecha de checkout es diferente a la que se reservara ", "Tue Feb 28 2023", checkOutEsperado);

        verticalScroll(driver.findElement(By.id(locScrollInput)), 700);
        List<WebElement> textTotalValue = driver.findElements(By.id(locTotalvalue));
        System.out.println("el valor del rooms value " + importeValueRooms);
        System.out.println("Que trae el locator total del final step " + textTotalValue.get(0).getText());
        Assert.assertEquals("EL importe mostrado no es el que fue seleccionado ", importeValueRooms, textTotalValue.get(0).getText());

    }

    public void verifMsjNoDestino(String textAlertaEsperado) {
        //verificar el mensaje trayendome el texto del id del mensaje y comparo con el esperado que lo tomo del escenario
        String textoAlert = this.obtenerTextoDeElemento(this.locAlertNoDestino);
        Assert.assertEquals("No se esta mostrando el mensaje de valñidación", textAlertaEsperado, textoAlert);

    }




}
