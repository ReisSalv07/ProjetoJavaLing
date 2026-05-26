package sistemaescolar;

import sistemaescolar.interfaces.MenuConsole;

public class Main {
    public static void main(String[] args) {
        // Dispara o menu principal do console
        MenuConsole menu = new MenuConsole();
        menu.iniciar();
    }
}
