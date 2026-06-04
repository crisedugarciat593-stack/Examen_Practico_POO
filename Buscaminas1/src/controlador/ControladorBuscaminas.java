package controlador;

import modelo.Tablero;
import modelo.Casilla;
import excepciones.PosicionInvalidaException;
import excepciones.CasillaYaDescubiertaException;
import java.util.Scanner;
import vista.Vista;
import java.io.IOException;

public class ControladorBuscaminas {
    private boolean juegoTerminado;
    private Tablero tablero;
    private Vista vista;

    public ControladorBuscaminas() {
        juegoTerminado = false;
        tablero = new Tablero();
        vista = new Vista();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (!juegoTerminado) {
            vista.mostrarTablero(tablero);
            System.out.print("Ingrese una coordenada (o 'guardar' / 'cargar' / 'salir'): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) {
                juegoTerminado = true;
                break;
            } else if (entrada.equalsIgnoreCase("guardar")) {
                try {
                    new GestorArchivos().guardarJuego(tablero, "partida.dat");
                    System.out.println("¡Partida guardada!");
                } catch (IOException e) {
                    System.out.println("Error al guardar: " + e.getMessage());
                }
                continue;
            } else if (entrada.equalsIgnoreCase("cargar")) {
                cargarPartida();
                continue;
            }

            if (coordenadaValida(entrada)) {
                procesarJugada(entrada);
            } else {
                System.out.println("Coordenada inválida o comando desconocido.");
            }
        }
        scanner.close();
    }

    private void procesarJugada(String coordenada) {
        int fila = obtenerFila(coordenada);
        int columna = obtenerColumna(coordenada);
        try {
            tablero.revelarCasilla(fila, columna);
            Casilla c = tablero.obtenerCasilla(fila, columna);
            if (c.isEsMina()) {
                System.out.println("¡HAS PERDIDO!");
                juegoTerminado = true;
            } else if (tablero.todasLasSegurasDescubiertas()) {
                System.out.println("¡HAS GANADO!");
                juegoTerminado = true;
            }
        } catch (PosicionInvalidaException | CasillaYaDescubiertaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cargarPartida() {
        try {
            this.tablero = new GestorArchivos().cargarJuego("partida.dat");
            System.out.println("¡Partida cargada correctamente!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar la partida: " + e.getMessage());
        }
    }

    private boolean coordenadaValida(String entrada) { return entrada.matches("^[A-Ja-j](10|[1-9])$"); }
    private int obtenerFila(String entrada) { return Character.toUpperCase(entrada.charAt(0)) - 'A'; }
    private int obtenerColumna(String entrada) { return Integer.parseInt(entrada.substring(1)) - 1; }
}