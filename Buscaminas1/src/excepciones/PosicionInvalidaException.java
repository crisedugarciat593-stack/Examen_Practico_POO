package excepciones;

public class PosicionInvalidaException extends Exception{

	public PosicionInvalidaException() {
		super("Error: Esa posicion no existe en el tablero");
	}
	
}

