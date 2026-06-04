package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CasillaTest {

    @Test
    public void testInicializacionCasilla() {
        Casilla casilla = new Casilla();
        
        // Verificamos el estado por defecto de una casilla nueva
        assertFalse(casilla.isEsMina(), "La casilla no debería ser mina por defecto");
        assertFalse(casilla.isDescubierta(), "La casilla debe estar cubierta por defecto");
        assertFalse(casilla.isMarcada(), "La casilla no debe estar marcada por defecto");
        assertEquals(0, casilla.getMinasAdyacentes(), "Debe tener 0 minas adyacentes al inicio");
    }

    @Test
    public void testModificarEstadoCasilla() {
        Casilla casilla = new Casilla();
        
        casilla.setEsMina(true);
        casilla.setDescubierta(true);
        casilla.setMinasAdyacentes(3);
        
        assertTrue(casilla.isEsMina(), "La casilla debería ser mina tras modificarla");
        assertTrue(casilla.isDescubierta(), "La casilla debería estar descubierta");
        assertEquals(3, casilla.getMinasAdyacentes(), "Debería tener 3 minas adyacentes");
    }
}