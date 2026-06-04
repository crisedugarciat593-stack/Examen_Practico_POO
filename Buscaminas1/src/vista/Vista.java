package vista;

import modelo.Tablero;
import modelo.Casilla;

public class Vista {
    
    public void mostrarTablero(Tablero tablero) {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < tablero.getFilas(); i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.obtenerCasilla(i, j);
                if (c.isDescubierta()) {
                    System.out.print(c.isEsMina() ? "* " : c.getMinasAdyacentes() + " ");
                } else {
                    System.out.print(c.isMarcada() ? "? " : ". ");
                }
            }
            System.out.println();
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void solicitarCoordenada() {
        System.out.print("Ingrese una coordenada (o 'guardar' / 'cargar' / 'salir'): ");
    }

    public void mostrarVictoria(String nombre) {
        System.out.println("¡FELICIDADES " + nombre.toUpperCase() + "! HAS GANADO LA PARTIDA.");
    }

    public void mostrarDerrota() {
        System.out.println("¡HAS PISADO UNA MINA! GAME OVER.");
    }

    public void mostrarTurno(String nombre) {
        System.out.println("--- Turno de: " + nombre + " ---");
    }
}