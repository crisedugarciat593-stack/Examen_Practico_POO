package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import excepciones.PosicionInvalidaException;
import excepciones.CasillaYaDescubiertaException;

public class TableroTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void testDimensionesTablero() {
        assertEquals(10, tablero.getFilas(), "El tablero debe tener 10 filas");
        assertEquals(10, tablero.getColumnas(), "El tablero debe tener 10 columnas");
    }

    @Test
    public void testCantidadMinasGeneradas() {
        int minasContadas = 0;
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (tablero.obtenerCasilla(i, j).isEsMina()) {
                    minasContadas++;
                }
            }
        }
        assertEquals(10, minasContadas, "Debe haber exactamente 10 minas en el tablero");
    }

    @Test
    public void testRevelarCasillaInvalidaLanzaExcepcion() {
        // Probamos una coordenada fuera de los límites
        assertThrows(PosicionInvalidaException.class, () -> {
            tablero.revelarCasilla(-1, 5);
        }, "Debe lanzar PosicionInvalidaException si la coordenada no existe");
        
        // Probamos límite superior
        assertThrows(PosicionInvalidaException.class, () -> {
            tablero.revelarCasilla(10, 10);
        });
    }

    @Test
    public void testRevelarCasillaYaDescubiertaLanzaExcepcion() throws Exception {
        // Buscamos una casilla segura (sin mina) para la prueba
        int filaSegura = 0, colSegura = 0;
        encontrarSegura:
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (!tablero.obtenerCasilla(i, j).isEsMina()) {
                    filaSegura = i;
                    colSegura = j;
                    break encontrarSegura;
                }
            }
        }

        // Descubrimos la casilla por primera vez
        tablero.revelarCasilla(filaSegura, colSegura);
        assertTrue(tablero.obtenerCasilla(filaSegura, colSegura).isDescubierta());

        // Intentamos descubrirla de nuevo, debe lanzar la excepción
        int f = filaSegura;
        int c = colSegura;
        assertThrows(CasillaYaDescubiertaException.class, () -> {
            tablero.revelarCasilla(f, c);
        }, "Debe lanzar CasillaYaDescubiertaException al revelar dos veces");
    }
    @Test
    public void testAlternarMarcaCasilla() throws Exception {
        // Marcamos la casilla en la esquina superior izquierda
        tablero.alternarMarca(0, 0);
        assertTrue(tablero.obtenerCasilla(0, 0).isMarcada(), "La casilla debería estar marcada con una bandera");
        
        // Volvemos a ejecutar el método en la misma coordenada para quitar la marca
        tablero.alternarMarca(0, 0);
        assertFalse(tablero.obtenerCasilla(0, 0).isMarcada(), "La casilla debería haberse desmarcado");
    }
}