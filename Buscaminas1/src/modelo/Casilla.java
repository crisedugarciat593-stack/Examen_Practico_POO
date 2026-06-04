package modelo;
import java.io.Serializable;
public class Casilla implements Serializable {
	
	private boolean esMina;
	private boolean descubierta;
	private boolean marcada;
	private int minasAdyacentes;
	
	public boolean isEsMina() {
		return esMina;
	}
	public void setEsMina(boolean esMina) {
		this.esMina = esMina;
	}
	public boolean isDescubierta() {
		return descubierta;
	}
	public void setDescubierta(boolean descubierta) {
		this.descubierta = descubierta;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	public int getMinasAdyacentes() {
		return minasAdyacentes;
	}
	public void setMinasAdyacentes(int minaAdyacentes) {
		this.minasAdyacentes = minaAdyacentes;
	}
	
}
