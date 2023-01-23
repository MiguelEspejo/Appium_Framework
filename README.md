# Framework de automatización con APPIUM
### Descripción:
##### Este es un framework creado la automatización de dispositivos moviles creado para evaluar la aplicación de Booking.com.
##### Booking.com es una página que ofrece servicios para viajes que te ayuda a reservar alojamientos, taxis, atracciones, etc...

###Herramientas
1. Java Jdk 17.
2. Junit 5.7
3. Cucumber java 7.10.1
4. Appium Java Client 8.3.0
5. ExtentReports 1.9.2
6. Jenkins

###Requerido
JDK 17
Appium Server
Appium Inspector
Emulador adb o Instalar Android Studio para utilizar su device

###Instrucciones
1. Clonar el repositorio: *https://github.com/MiguelEspejo/Appium_Framework.git*
2. Ejecutar en la terminal el comando: `gradle clean`
3. Ejecutar en la terminal el comando: `gradle build`
4. Ejecutar en la terminal el comando para ejecutar el smoke test del servicio.  `gradle test -DcucumberOptions="--tags @Smoke" ` 

