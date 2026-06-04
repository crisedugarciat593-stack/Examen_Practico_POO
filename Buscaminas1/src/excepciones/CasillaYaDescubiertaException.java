package excepciones;

public class CasillaYaDescubiertaException extends Exception{

	public CasillaYaDescubiertaException() {
		super("Esa casilla ya fue descubierta");
	}
	
}

