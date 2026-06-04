
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
    
    
    //Este es el metodo principal que controla el ciclo del juego
    public void iniciar() {

        Scanner scanner = new Scanner(System.in);

        while (!juegoTerminado) {

            System.out.print("Ingrese una coordenada (ejemplo A5): ");

            String coordenada = scanner.nextLine();
            
            //Permite terminar el juego de forma manual
            if (coordenada.equalsIgnoreCase("salir")) {

                juegoTerminado = true;

                System.out.println("Juego finalizado.");

                break;
            }
            
            // Verifica que la coordenada tenga el formato correcto
            if (coordenadaValida(coordenada)) {

                procesarJugada(coordenada);

            } else {

                System.out.println("Coordenada inválida.");

            }

        }

    }
    
    //Procesa la jugada ingresada por el usuario revelando la casilla seleccionada
    //y verificando la condicion de victoria o derrota
    private void procesarJugada(String coordenada) {

        int fila = obtenerFila(coordenada);
        int columna = obtenerColumna(coordenada);

        try {
        	
        	//revela la casilla elegida
            tablero.revelarCasilla(fila, columna);

            Casilla casilla =
                    tablero.obtenerCasilla(fila, columna);

            System.out.println("Casilla seleccionada:");
            System.out.println("Fila: " + fila);
            System.out.println("Columna: " + columna);
            
            //si la casilla tiene una mina el jugador pierde
            if (casilla.isEsMina()) {

                System.out.println("HAS PERDIDO XD");

                juegoTerminado = true;
            }
            
            //pero si todas las casillas seguras fueron descubiertas, el jugador gana
            if (tablero.todasLasSegurasDescubiertas()) {

                System.out.println("HAS GANADO!");

                juegoTerminado = true;
            }
           

        } catch (PosicionInvalidaException e) {
        	
        	//Se captura cuando la posicion esta fuera del tablero
            System.out.println(e.getMessage());

        } catch (CasillaYaDescubiertaException e) {
        	
        	//y aqui se captura cuando intentan abrir una casilla ya descubierta
            System.out.println(e.getMessage());

        }

    }
    
    //Verifica que la coordenada sea en un formato valido
    private boolean coordenadaValida(String entrada) {

        return entrada.matches("^[A-Ja-j](10|[1-9])$");

    }
    
    //Convierte la letra de la coordenada en el indice de fila que corresponde
    private int obtenerFila(String entrada) {

        return Character.toUpperCase(
                entrada.charAt(0)) - 'A';

    }
    
    //Y aqui convierte el numero de la coordenada en el indice de columa que corresponde
    private int obtenerColumna(String entrada) {

        return Integer.parseInt(
                entrada.substring(1)) - 1;

    }

}


