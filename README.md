#  Buscaminas (Se despliega en consola) - Examen Practico POO

Proyecto desarrollado para la asignatura de Programación Orientada a Objetos de la Universidad Politécnica Salesiana. Es una implementación en consola del clásico juego Buscaminas, desarrollado en Java aplicando principios de Diseño de Software, Patrón MVC y Desarrollo Guiado por Pruebas (TDD).

# Equipo

* Geovanny Steven Sanchez Maza
* Cristian Eduardo Garcia Torres
* Luis Fernando Fuelagan Pujota
* Steven Javier Estupiñan Guaman

# Características 
* Arquitectura MVC: Separación estricta entre el Modelo (lógica y datos), Vista (interfaz de consola) y Controlador (gestión de turnos y entradas).
* Multijugador por Turnos: Soporte para hasta 4 jugadores compitiendo por descubrir la mayor cantidad de casillas seguras.
* Sistema de Marcado (Banderas): Permite proteger casillas sospechosas de contener minas.
* Persistencia de Datos: Capacidad para guardar la partida actual y reanudarla más tarde mediante serialización de objetos.
* Manejo Seguro de Errores: Implementación de excepciones personalizadas (`PosicionInvalidaException`, `CasillaYaDescubiertaException`).



# Instrucciones de Instalación

# Requisitos Previos
* Java Development Kit (JDK) 17 o superior.
* Un IDE compatible con Java (Visual Studio Code, Eclipse) o terminal con `javac`.
* Git para clonar el repositorio.

### Pasos para ejecutar
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/crisedugarciat593-stack/Examen_Practico_POO.git
   cd Examen_Practico_POO