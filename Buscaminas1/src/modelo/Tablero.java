package modelo;

import excepciones.PosicionInvalidaException;
import excepciones.CasillaYaDescubiertaException;
import java.util.Random;

public class Tablero {
	
	private Casilla[][] casillas;
	
	private static final int FILAS = 10;
	private static final int COLUMNAS = 10;
	private static final int TOTAL_MINAS = 10;
	
	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
		colocarMinas();
		calcularAdyacentes();
	}
	public void colocarMinas() {
		
		Random random = new Random();
		int minasColocadas = 0;
		
		while (minasColocadas < TOTAL_MINAS) {
			int fila = random.nextInt(FILAS);
			int columna = random.nextInt(COLUMNAS);
			
			if (!casillas[fila][columna].isEsMina()) {
				casillas[fila][columna].setEsMina(true);
				minasColocadas++;
			}
		}
	}
	private void calcularAdyacentes() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				
				if (!casillas[i][j].isEsMina()) {
					casillas[i][j].setMinasAdyacentes(contadorMinasAlrededor(i, j));
				}
			}
		}
	}
	private int contadorMinasAlrededor(int fila, int columna) {
		int contador = 0;
		
		for (int i = fila - 1; i <= fila + 1; i++) {
			for (int j = columna - 1; j <= columna + 1; j++) {
				//Verifica que no se salga del tablero
				if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {
					if (casillas[i][j].isEsMina()) {
						contador++;
					}
				}
			}
		}
		return contador;
	}
	public void revelarCasilla(int fila, int col) throws PosicionInvalidaException, CasillaYaDescubiertaException {

	    // Verifica que la posicion exista
	    if (fila < 0 || fila >= FILAS || col < 0 || col >= COLUMNAS) {
	        throw new PosicionInvalidaException();
	    }
	    Casilla casilla = casillas[fila][col];
	    
	    // Verifica que no este ya descubierta
	    if (casilla.isDescubierta()) {
	        throw new CasillaYaDescubiertaException();
	    }
	    // No revela casillas marcadas
	    if (casilla.isMarcada()) {
	        return;
	    }
	    // Descubre la casilla
	    casilla.setDescubierta(true);

	    // Si no tiene minas alrededor y no es mina, revela las casillas vecinas tambien
	    if (!casilla.isEsMina() && casilla.getMinasAdyacentes() == 0) {
	        for (int i = fila - 1; i <= fila + 1; i++) {
	            for (int j = col - 1; j <= col + 1; j++) {
	                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {
	                    if (!casillas[i][j].isDescubierta()) {
	                        revelarCasilla(i, j);
	                    }
	                }
	            }
	        }
	    }
	}
	//Obtener una casilla especifica
	public Casilla obtenerCasilla(int fila, int columna) {
		return casillas[fila][columna];
	}
	public int getFilas() {
		return FILAS;
	}
	public int getColumnas() {
		return COLUMNAS;
	}
}


