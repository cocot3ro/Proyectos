package cli;

import java.util.Scanner;

import motorBM.Buscaminas;

public class InterfaceCLI{
    Scanner sc = new Scanner(System.in);

    Buscaminas bm;

    int numBanderas;
    boolean partidaEmpezada;

    InterfaceCLI() {
        this.bm = new Buscaminas(pedirDificultad());

        this.juego();

        sc.close();
    }

    int[] pedirDificultad() {
        System.out.println("Selecciona dificultad: [F]acil, [I]ntermedio, [D]ificil, [O]tro");

        while (true) {
            char dificultad = sc.next().trim().toUpperCase().charAt(0);
            switch (dificultad) {
                case 'F':
                    return new int[] { 9, 9, 10 };
                case 'I':
                    return new int[] { 16, 16, 40 };
                case 'D':
                    return new int[] { 16, 30, 99};
                case 'O':
                    System.out.println("Indica número de filas: ");
                    int filas = sc.nextInt();

                    System.out.println("Indica número de columnas: ");
                    int columnas = sc.nextInt();

                    System.out.println("Indica número de minas: ");
                    int minas = sc.nextInt();

                    return new int[] { filas, columnas, minas };
                default:
                    System.out.println("\nOpcion no válida");
                    break;
            }
        }
    }

    void juego() {
        do {
            imprimirTablero();
            if (!partidaEmpezada) {
                primeraJugada();
                this.partidaEmpezada = true;
            } else {
                pedirOpcion();
            }
        } while (!bm.esFinPartida());

        imprimirTablero();

        if (bm.esVictoria()) {
            System.out.println("HAS GANADO :)");
        } else {
            System.out.println("Has perdido X_X");
        }
    }

    private void primeraJugada() {
        bm.primeraJugada(pedirCasilla());
    }

    void imprimirTablero() {
        char[][] temp = bm.getTablero();

        int numMinas = bm.getCANTIDAD_MINAS() - numBanderas;

        System.out.println("Minas restantes: " + numMinas);
        System.out.print("  ");
        for (int i = 0; i < temp[0].length; i++) {
            if (i < 10) {
                System.out.print("   " + i);
            } else {
                System.out.print("  " + i);
            }
        }
        System.out.println();

        System.out.print("   ┌");
        for (int i = 0; i < temp[0].length - 1; i++) {
            System.out.print("───┬");
        }
        System.out.println("───┐");

        for (int i = 0; i < temp.length; i++) {
            if (i < 10) {
                System.out.print(" " + i + " │");
            } else {
                System.out.print(i + " │");
            }

            for (int j = 0; j < temp[i].length; j++) {
                System.out.print(" " + temp[i][j] + " │");
            }
            System.out.println();

            if (i != temp.length - 1) {
                System.out.print("   ├");
                for (int j = 0; j < temp[i].length - 1; j++) {
                    System.out.print("───┼");
                }
                System.out.println("───┤");
            }
        }
        System.out.print("   └");
        for (int i = 0; i < temp[0].length - 1; i++) {
            System.out.print("───┴");
        }
        System.out.println("───┘");
    }

    int[] pedirCasilla(int opc) {
        System.out.println("");

        while (true) {
            System.out.println("Selecciona posicion: [0-" + (bm.getTablero().length - 1) + "] [0-"
                    + (bm.getTablero()[0].length - 1) + "]");

            int[] casilla = { sc.nextInt(), sc.nextInt() };

            if (!(bm.coordenadaCorrecta(casilla))) {
                System.out.println("\n\n\nLa coordenada [" + casilla[0] + " " + casilla[1] + "] no es correcta.");
                System.out.println("\n");
            } else if (bm.esVisible(casilla)) {
                System.out.println("\n\n\nEsta posicion ya esta descubierta");
                System.out.println("Por favor, indica otra posicion\n\n");
            } else if (opc != 1 && bm.tieneBandera(casilla)) {
                System.out.println("\n\n\nLa posición tiene una bandera");
                System.out.println("Por favor, indica otra posicion\n\n");
            } else {
                return casilla;
            }
        }
    }

    int[] pedirCasilla() {
        System.out.println("");

        while (true) {
            System.out.println("Selecciona posicion: [0-" + (bm.getTablero().length - 1) + "] [0-"
                    + (bm.getTablero()[0].length - 1) + "]");

            int[] casilla = { sc.nextInt(), sc.nextInt() };

            if (!(bm.coordenadaCorrecta(casilla))) {
                System.out.println("\n\n\nLa coordenada [" + casilla[0] + " " + casilla[1] + "] no es correcta.");
                System.out.println("\n");
            } else if (bm.esVisible(casilla)) {
                System.out.println("\n\n\nEsta posicion ya esta descubierta");
                System.out.println("Por favor, indica otra posicion\n\n");
            } else if (bm.tieneBandera(casilla)) {
                System.out.println("\n\n\nLa posición tiene una bandera");
                System.out.println("Por favor, indica otra posicion\n\n");
            } else {
                return casilla;
            }
        }
    }

    void pedirOpcion() {

        int opcion;

        while (true) {
            System.out.println("Que quieres hacer:");
            System.out.println("1: Poner/quitar bandera");
            System.out.println("2: Descubir casilla");
            System.out.println("3: Reiniciar");

            opcion = sc.nextInt();

            if (opcion < 1 || opcion > 3) {
                System.out.println("\n\n\nOpcion no valida\n");
            } else {
                break;
            }
        }

        if (opcion == 3) {
            this.numBanderas = 0;
            this.partidaEmpezada = false;
            this.bm = new Buscaminas(pedirDificultad());
        }

        int[] casilla = pedirCasilla(opcion);

        switch (opcion) {
            case 1:
                numBanderas = (bm.tieneBandera(casilla) ? numBanderas - 1 : numBanderas + 1);
                bm.cambiarBandera(casilla);
                break;

            case 2:
                bm.hacerJugada(casilla);
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        new InterfaceCLI();
    }

}
