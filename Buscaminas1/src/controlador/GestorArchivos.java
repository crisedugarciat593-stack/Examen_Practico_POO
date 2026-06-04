package controlador;

import modelo.Tablero;
import java.io.*;

public class GestorArchivos {
    public void guardarJuego(Tablero tablero, String nombreArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(tablero);
        }
    }

    public Tablero cargarJuego(String nombreArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Tablero) ois.readObject();
        }
    }
}