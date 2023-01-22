@Smoke_Booking
Feature: Revisar las funcionalidades disponibles en la app booking.com

  Rule: Revisaremos las funcionalidades del servicio de alojamiento en la aplicación de booking.com

    Scenario Outline: Verificar que se permita realizar una busqueda de alojamientos con diferentes destinos
      Given Acceso a la aplicacion booking.com
      When Capturo el Destino y selecciono Cusco
      And Selecciono la Fecha Inicio 14 February 2023 y Fecha Fin 28 February 2023 del alojamiento
      When Capturo la cantidad de cuartos 1 adultos 1 y ninos 5 anos
      And Presiono tap en el boton search
      And Selecciono algun resultado en el resultado de la busqueda
      When Selecciono y reservo la habitacion
      And Capturo la siguiente informacion del huesped nombre <Nombre> apellido <Apellido> email <Email> direccion <Direccion> CP <CodigoPostal> ciudad <Ciudad> pais <Pais> telefono <Telefono>
      Then Verifico la informacion capturada en reserva del alojamiento
      Examples:
        | Nombre  | Apellido | Email              | Direccion      | CodigoPostal | Ciudad          | Pais   | Telefono   |
        | Roberto | Lopez    | correo@hotmail.com | Zacatecas 1559 | 81200        | Ciudad Conocida | Mexico | 6681204050 |


