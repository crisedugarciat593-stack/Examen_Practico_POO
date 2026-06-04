package modelo;

import java.io.Serializable;

/**
 * Clase Jugador que representa al usuario del juego.
 * Implementa Serializable para permitir el guardado de estado.
 */
public class Jugador implements Serializable {
    // Identificador único para la serialización
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int partidasGanadas;
    private int partidasPerdidas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void incrementarVictorias() {
        this.partidasGanadas++;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void incrementarDerrotas() {
        this.partidasPerdidas++;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + " | Victorias: " + partidasGanadas + " | Derrotas: " + partidasPerdidas;
    }
}