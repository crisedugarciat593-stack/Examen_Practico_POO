package controlador;

import modelo.Tablero;
import modelo.Jugador;
import java.io.*;
import java.util.List;

public class GestorArchivos {
    public void guardarJuego(Tablero tablero, List<Jugador> jugadores, int turnoActual, String nombreArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(tablero);
            oos.writeObject(jugadores);
            oos.writeInt(turnoActual);
        }
    }
    @SuppressWarnings("unchecked")
    public Object[] cargarJuego(String nombreArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return new Object[]{ (Tablero) ois.readObject(), (List<Jugador>) ois.readObject(), ois.readInt() };
        }
    }
}