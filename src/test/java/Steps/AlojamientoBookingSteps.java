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
    public void capturoDestino(String destino) throws InterruptedException {
        alojamientoBooking.clicEnInputDestino();

        alojamientoBooking.capturaDestino(destino);
        alojamientoBooking.clicEnResultadoDestino();
    }

    @And("^Selecciono la Fecha Inicio 14 February 2023 y Fecha Fin 28 February 2023 del alojamiento$")
    public void seleccionaFechas() throws InterruptedException {
        alojamientoBooking.seleccionarFechasAlojamiento();
    }

    @When("^Capturo la cantidad de cuartos (.+) adultos (.+) y ninos (.+) anos$")
    public void seleccionoCuartoPersonas(String cuartos, String adultos, String ninos) throws InterruptedException {
        alojamientoBooking.seleccionarOcupantes(cuartos, adultos, ninos);
    }

    @And("^Presiono tap en el boton search$")
    public void seleccionoCuartoPersonas() throws InterruptedException {
        alojamientoBooking.selBtnSearch();
    }

    @And("^Selecciono algun resultado en el resultado de la busqueda$")
    public void selectResultadosAlojamiento() throws InterruptedException {
        alojamientoBooking.selResultadoSearch();

    }

    @When("^Selecciono y reservo la habitacion$")
    public void selectHabitacion() throws InterruptedException {
        alojamientoBooking.selecionaHabitacion();
    }

    @And("^Capturo la siguiente informacion del huesped nombre (.+) apellido (.+) email (.+) direccion (.+) CP (.+) ciudad (.+) pais (.+) telefono (.+)$")
    public void capInfoHuesped(String nombre, String apellido, String email, String direccion, String codigoPostal, String ciudad, String pais, String telefono) throws InterruptedException {
        alojamientoBooking.capturaDatosHuesp(nombre, apellido, email, direccion, codigoPostal, ciudad, pais, telefono);
    }

    @And("^Verifico la informacion capturada en reserva del alojamiento$")
    public void verificarInfoOvervieew() throws InterruptedException {
        alojamientoBooking.verificarAlojamiento();

    }


}
