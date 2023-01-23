package Steps;

import Pages.AlojamientoPage;
import io.cucumber.java.en.*;

public class AlojamientoBookingSteps {

    AlojamientoPage alojamientoBooking = new AlojamientoPage();

    @Given("^Acceso a la aplicacion booking.com$")
    public void ejecutarAndroidapp() {

        alojamientoBooking.abrirAplicacionBooking();
    }

    @When("^Capturo el Destino y selecciono (.+)$")
    public void capturoDestino(String destino) {

        alojamientoBooking.clicEnInputDestino();
        alojamientoBooking.capturaDestino(destino);
        alojamientoBooking.clicEnResultadoDestino();
    }

    @And("^Selecciono la Fecha Inicio 14 February 2023 y Fecha Fin 28 February 2023 del alojamiento$")
    public void seleccionaFechas() {
        alojamientoBooking.seleccionarFechasAlojamiento();
    }

    @When("^Capturo la cantidad de cuartos (.+) adultos (.+) y ninos (.+) anos$")
    public void seleccionoCuartoPersonas(String cuartos, String adultos, String ninos) {
        alojamientoBooking.seleccionarOcupantes(cuartos, adultos, ninos);
    }

    @And("^Presiono tap en el boton search$")
    public void seleccionoCuartoPersonas() {
        alojamientoBooking.selBtnSearch();
    }

    @And("^Selecciono algun resultado en el resultado de la busqueda$")
    public void selectResultadosAlojamiento() {
        alojamientoBooking.selResultadoSearch();

    }

    @When("^Selecciono y reservo la habitacion$")
    public void selectHabitacion() {
        alojamientoBooking.selecionaHabitacion();
    }

    @And("^Capturo la siguiente informacion del huesped nombre (.+) apellido (.+) email (.+) direccion (.+) CP (.+) ciudad (.+) pais (.+) telefono (.+)$")
    public void capInfoHuesped(String nombre, String apellido, String email, String direccion, String codigoPostal, String ciudad, String pais, String telefono) {
        alojamientoBooking.capturaDatosHuesp(nombre, apellido, email, direccion, codigoPostal, ciudad, pais, telefono);
    }

    @And("^Verifico la informacion capturada en reserva del alojamiento$")
    public void verificarInfoOvervieew() {
        alojamientoBooking.verificarAlojamiento();

    }

    @When("^Selecciono el boton search sin capturar destino$")
    public void selBtnSearcNoDestino() {
        alojamientoBooking.selBtnSearch();
    }
    @When("^Verifico el mensaje de validacion (.+) y presiono Ok$")
    public void validacionAlertNoDestino(String msjValEsperado) {
        alojamientoBooking.verifMsjNoDestino(msjValEsperado);
    }

}
