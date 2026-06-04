
package controlador;

import modelo.Tablero;
import modelo.Casilla;
import excepciones.PosicionInvalidaException;
import excepciones.CasillaYaDescubiertaException;
import java.util.Scanner;

public class ControladorBuscaminas {

    private boolean juegoTerminado;
    private Tablero tablero;
    

    public ControladorBuscaminas() {
        juegoTerminado = false;
        tablero = new Tablero();
    }
    
    
    public void iniciar() {

        Scanner scanner = new Scanner(System.in);

        while (!juegoTerminado) {

            System.out.print("Ingrese una coordenada (ejemplo A5): ");

            String coordenada = scanner.nextLine();

            if (coordenada.equalsIgnoreCase("salir")) {

                juegoTerminado = true;

                System.out.println("Juego finalizado.");

                break;
            }

            if (coordenadaValida(coordenada)) {

                procesarJugada(coordenada);

            } else {

                System.out.println("Coordenada inválida.");

            }

        }

    }

    private void procesarJugada(String coordenada) {

        int fila = obtenerFila(coordenada);
        int columna = obtenerColumna(coordenada);

        try {

            tablero.revelarCasilla(fila, columna);

            Casilla casilla =
                    tablero.obtenerCasilla(fila, columna);

            System.out.println("Casilla seleccionada:");
            System.out.println("Fila: " + fila);
            System.out.println("Columna: " + columna);

            if (casilla.isEsMina()) {

                System.out.println("HAS PERDIDO XD");

                juegoTerminado = true;
            }
            
            if (tablero.todasLasSegurasDescubiertas()) {

                System.out.println("🏆 ¡HAS GANADO!");

                juegoTerminado = true;
            }
           

        } catch (PosicionInvalidaException e) {

            System.out.println(e.getMessage());

        } catch (CasillaYaDescubiertaException e) {

            System.out.println(e.getMessage());

        }

    }

    private boolean coordenadaValida(String entrada) {

        return entrada.matches("^[A-Ja-j](10|[1-9])$");

    }

    private int obtenerFila(String entrada) {

        return Character.toUpperCase(
                entrada.charAt(0)) - 'A';

    }

    private int obtenerColumna(String entrada) {

        return Integer.parseInt(
                entrada.substring(1)) - 1;

    }

}


