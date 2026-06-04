package principal;

import controlador.ControladorBuscaminas;

public class Main {
    public static void main(String[] args) {
        ControladorBuscaminas juego = new ControladorBuscaminas();
        juego.iniciar();
    }
}