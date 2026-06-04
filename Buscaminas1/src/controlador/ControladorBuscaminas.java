package controlador;

import modelo.*;
import vista.Vista;
import excepciones.PosicionInvalidaException; 

import java.util.*;
import java.io.IOException;

public class ControladorBuscaminas {
    private Tablero tablero;
    private Vista vista;
    private List<Jugador> jugadores;
    private int turnoActual;
    private boolean juegoTerminado;

    public ControladorBuscaminas() {
        this.tablero = new Tablero();
        this.vista = new Vista();
        this.jugadores = new ArrayList<>();
        this.turnoActual = 0;
        for (int i = 1; i <= 4; i++) jugadores.add(new Jugador("Jugador " + i));
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            this.tablero = new Tablero();
            this.juegoTerminado = false;
            
            while (!juegoTerminado) {
                Jugador jugadorActual = jugadores.get(turnoActual);
                vista.mostrarTurno(jugadorActual.getNombre());
                vista.mostrarTablero(tablero);
                vista.solicitarCoordenada();
                String entrada = scanner.nextLine().trim();

                if (entrada.equalsIgnoreCase("salir")) { 
                    salir = true; 
                    juegoTerminado = true; 
                }
                else if (entrada.equalsIgnoreCase("guardar")) guardarPartida();
                else if (entrada.equalsIgnoreCase("cargar")) cargarPartida();
                
                //  LÓGICA PARA MARCAR CASILLAS 
                else if (entrada.toUpperCase().startsWith("M ")) {
                    String coordenada = entrada.substring(2).trim();
                    if (coordenada.matches("(?i)[A-J](10|[1-9])")) {
                        marcarCasilla(coordenada);
                    } else {
                        vista.mostrarMensaje("Coordenada inválida para marcar.");
                    }
                }
                // -----------------------------------------
                
                else if (entrada.matches("(?i)[A-J](10|[1-9])")) procesarJugada(entrada, jugadorActual);
                else vista.mostrarMensaje("Coordenada inválida.");
            }
            if (!salir) {
                vista.mostrarMensaje("¿Jugar otra vez? (s/n)");
                if (!scanner.nextLine().equalsIgnoreCase("s")) salir = true;
                else turnoActual = 0; // Reinicia los turnos para la nueva partida
            }
        }
        scanner.close();
    }

    private void procesarJugada(String coord, Jugador jugador) {
        int f = obtenerFila(coord);
        int c = obtenerColumna(coord);
        try {
            tablero.revelarCasilla(f, c);
            if (tablero.obtenerCasilla(f, c).isEsMina()) {
                jugador.incrementarDerrotas();
                vista.mostrarDerrota();
                juegoTerminado = true;
            } else if (tablero.todasLasSegurasDescubiertas()) {
                jugador.incrementarVictorias();
                vista.mostrarVictoria(jugador.getNombre());
                juegoTerminado = true;
            } else {
                turnoActual = (turnoActual + 1) % jugadores.size(); // Cambia de turno solo si no pierde ni gana
            }
        } catch (Exception e) { 
            vista.mostrarMensaje(e.getMessage()); 
        }
    }

    private void guardarPartida() {
        try { 
            new GestorArchivos().guardarJuego(tablero, jugadores, turnoActual, "partida.dat"); 
            vista.mostrarMensaje("Guardado."); 
        } catch (IOException e) { 
            vista.mostrarMensaje("Error al guardar."); 
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarPartida() {
        try {
            Object[] data = new GestorArchivos().cargarJuego("partida.dat");
            this.tablero = (Tablero) data[0]; 
            this.jugadores = (List<Jugador>) data[1]; 
            this.turnoActual = (int) data[2];
            vista.mostrarMensaje("Partida cargada.");
        } catch (Exception e) { 
            vista.mostrarMensaje("Error al cargar."); 
        }
    }

    private void marcarCasilla(String coordenada) {
        int fila = obtenerFila(coordenada);
        int columna = obtenerColumna(coordenada);
        try {
            tablero.alternarMarca(fila, columna);
            // Opcional: Puedes decidir si marcar una casilla consume un turno o no.
            // Si quieres que consuma el turno, descomenta la siguiente línea:
            // turnoActual = (turnoActual + 1) % jugadores.size();
        } catch (PosicionInvalidaException e) {
            vista.mostrarMensaje("Error al marcar: " + e.getMessage());
        }
    }


    private int obtenerFila(String entrada) { 
        return Character.toUpperCase(entrada.charAt(0)) - 'A'; 
    }
    
    private int obtenerColumna(String entrada) { 
        return Integer.parseInt(entrada.substring(1)) - 1; 
    }
}