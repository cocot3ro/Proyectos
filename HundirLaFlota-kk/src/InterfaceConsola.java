import java.util.Scanner;

import assets.*;

public class InterfaceConsola {
    Scanner sc = new Scanner(System.in);

    HundirLaFlota hlf;
    
    InterfaceConsola() {
        hlf = new HundirLaFlota();
        
        this.presentarJuego();
        
        this.setTablerosFlota();
    }

    void presentarJuego() {
        System.out.println("=====================");
        System.out.println("|| HUNDIR LA FLOTA |│");
        System.out.println("=====================");
        System.out.println("Juego clásico de hundir la flota donde el jugador juega contra la máquina");
        System.out.print("El jugador tiene dos tableros: ");
        System.out.println("uno para sus barcos y otro para los disparos realizados.");
        System.out.println("Cada jugador cuenta con " + hlf.NUMERO_BARCOS + " barcos.");
        System.out.println("El objetivo es destruir todos los barcos del rival.");
        System.out.println("Para ello el jugador alterna turnos de ataque con la máquina.");
        System.out.println("Primero se colocan los barcos, luego comienza a atacar el jugador.");
        System.out.println("\nPulsa ENTER para continuar.");
        sc.nextLine();
    }

    void setTablerosFlota() {
        this.setFlotaJugador();

        this.cambiarTableroFlotaJugador();

        this.setFlotaMáquina();

        //this.mostrarTablero(hlf.getTableroFlotaMáquina());

        this.juego();
    }

    void setFlotaJugador() {
        this.colocarBarcosJugador(Estados.PORTAAVIONES);
        this.colocarBarcosJugador(Estados.SUBMARINO);
        this.colocarBarcosJugador(Estados.FRAGATA);
        this.colocarBarcosJugador(Estados.FRAGATA);
    }

    void colocarBarcosJugador(Estados estado) {
        bucleColocarBarcosJugador:
        while (true) {
            System.out.println("\n[COLOCANDO BARCOS]");
            System.out.println("Este es tu tablero");
            this.mostrarTablero(hlf.getTableroFlotaJugador());
            System.out.println("\nDónde quieres colocar "
            + (hlf.getNombreBarco(estado).equals("fragata")?"la " : "el ")
            + hlf.getNombreBarco(estado) + "? (ocupa " + hlf.getTamañoBarco(estado) + " espacios).");
            System.out.println("Introduce dos coordenadas con formato [A-G][1-7], ");

            System.out.print("\nCoordenada de inicio: ");
            Punto2D p1 = this.pedirCoordenada();

            System.out.print("\nCoordenada de fin: ");
            Punto2D p2 = this.pedirCoordenada();

            if (!(p1.getFila() == p2.getFila() ^ p1.getCol() == p2.getCol())
            || (p1.dist(p2) != hlf.getTamañoBarco(estado))) {
                System.out.println("¡ERROR!: Coordenadas no validas");
                System.out.print("Debes colocar tu barco en una fila o columna");
                System.out.print(", nunca en diagonal");
                System.out.println(", y respetando el tamaño del barco.");
                continue bucleColocarBarcosJugador;
            } else if (hlf.yaHayBarco(hlf.getTableroFlotaJugador(), p1, p2)) {
                System.out.println("¡ERROR!: Ya hay un barco en esta posición");
                continue bucleColocarBarcosJugador;
            } else {
                hlf.colocarBarcoJugador(p1, p2, estado);
                break bucleColocarBarcosJugador;
            }
        }
    }

    void cambiarTableroFlotaJugador() {

        System.out.println("\n\n\n\n\nAsí queda tu tablero de flota");
        this.mostrarTablero(hlf.getTableroFlotaJugador());
        System.out.print("\nQuieres cambiar el tablero? (S/N) ");

        char opc;
        bucleCambiarTablero:
        while (true) {
            switch (opc = sc.next().trim().toUpperCase().charAt(0)) {
                case 'S':
                    hlf.resetTableroFlotaJugador();
                    this.setTablerosFlota();
                    break;

                case 'N':
                    break;
                
                default:
                    System.out.println("\n\n\nLa opción [" + opc + "] no es válida");
                    System.out.println("\nQuieres cambiar el tablero? (S/N) ");
                    continue bucleCambiarTablero;
            }
            break bucleCambiarTablero;
        }
        sc.nextLine();
        System.out.println();
    }

    void setFlotaMáquina() {
        this.colocarBarcosMáquina(Estados.PORTAAVIONES);
        this.colocarBarcosMáquina(Estados.SUBMARINO);
        this.colocarBarcosMáquina(Estados.FRAGATA);
        this.colocarBarcosMáquina(Estados.FRAGATA);
    }

    void colocarBarcosMáquina(Estados tipoBarco) {
        bucleColocarBarcosMáquina:
        while (true) {
            Punto2D p1, p2;
            int cord1 = hlf.coordenadaAleatoria(hlf.TAMAÑO_TABLERO);
            int cord2 = hlf.coordenadaAleatoria((hlf.TAMAÑO_TABLERO - hlf.getTamañoBarco(tipoBarco)));
            int cord3 = cord2 + hlf.getTamañoBarco(tipoBarco) -1;

            if (hlf.randomBool()) { // el barco se situa en una fila
                p1 = hlf.nuevaCoordenada(cord1, cord2);
                p2 = hlf.nuevaCoordenada(cord1, cord3);

            } else { // el barco se situa en una columna
                p1 = hlf.nuevaCoordenada(cord2, cord1);
                p2 = hlf.nuevaCoordenada(cord3, cord1);
            }

            if (hlf.yaHayBarco(hlf.getTableroFlotaMáquina(), p1, p2)) {
                continue bucleColocarBarcosMáquina;
            } else {
                hlf.colocarBarcoMáquina(p1, p2, tipoBarco);
                break bucleColocarBarcosMáquina;
            }
        }
    }

    void juego() {
        while (true) {
            this.turnoJugador();
            if (hlf.ganaJugador()) {
                hlf.sleep(2);
                System.out.println("\n\n\n\n\n¡ENHORABUENA! Has ganado");
                break;
            }

            this.turnoMáquina();
            if (hlf.ganaMáquina()){
                hlf.sleep(2);
                System.out.println("\n\n\n\n\nHas sido derrotado...");
                break;
            }
        }

        this.resumenPartida();
    }

    void turnoJugador() {
        System.out.println("\n\n[TURNO DEL JUGADOR]");
        System.out.println("===================\n");
        hlf.sleep(0.5);

        this.mostrarTableros(hlf.getTableroFlotaJugador(), hlf.getTableroDisparosJugador());
        
        hlf.sleep(0.5);
        System.out.println("\nIndica una cordenada [A-G][1-7] para disparar un torpedo.");

        Punto2D p = this.pedirCoordenada();

        System.out.print("\nDisparando en " + p + " ");

        for (int i = 0; i < 3; i++) {
            hlf.sleep(0.65);
            System.out.print(".");
        }
        System.out.println("\n");

        Estados disparoJugador = hlf.jugadorDisparaTorpedo(p);

        switch (disparoJugador) {
            case VACIO:
            case AGUA:
                System.out.println("Has fallado.");
                break;

            case AGUA_IMP:
                System.out.println("Ya habías disparado aquí, solo hay agua");
                break;

            case PORTAAVIONES:
                System.out.println("¡HAS ACERTADO!");
                System.out.println("Portaaviones alcanzado");
                break;

            case PORTAAVIONES_IMP:
                System.out.println("Ya le habías dado al portaaviones en este punto!");
                break;

            case SUBMARINO:
                System.out.println("¡HAS ACERTADO!");
                System.out.println("Submarino alcanzado");
                break;

            case SUBMARINO_IMP:
                System.out.println("Ya le habías dado al submarino en este punto!");
                break;

            case FRAGATA:
                System.out.println("¡HAS ACERTADO!");
                System.out.println("Fragata alcanzada");
                break;

            case FRAGATA_IMP:
                System.out.println("Ya le habías dado a la fragata en este punto!");
                break;
        }
    }

    void turnoMáquina() {
        hlf.sleep(1.5);
        System.out.println("\n\n[TURNO DE LA MÁQUINA]");
        System.out.println("=====================\n");

        hlf.sleep(0.5);

        Punto2D p;

        bucleCoordenada:
        while(true) {
            p = hlf.coordenadaAleatoria();
            switch (hlf.getTableroDisparosMáquina()[p.getFila()][p.getCol()]) {
                case VACIO: case AGUA: case PORTAAVIONES: case SUBMARINO: case FRAGATA:
                    break bucleCoordenada;
            
                case AGUA_IMP: case PORTAAVIONES_IMP: case SUBMARINO_IMP: case FRAGATA_IMP:
                    break;
            }
        }

        Estados disparoMáquina = hlf.máquinaDisparaTorpedo(p);

        System.out.println("La máquina dispara en " + p);

        hlf.sleep(1);
        
        switch (disparoMáquina) {
            case AGUA:
                System.out.println("La máquina ha fallado");
                break;
            case PORTAAVIONES:
                System.out.println("La máquina ha acertado en tu portaaviones");
                break;
            case SUBMARINO:
                System.out.println("La máquina ha acertado en tu submarino");
                break;
            case FRAGATA:
                System.out.println("La máquina ha acertado en una de tus fragatas");
                break;
            default:
                break;
        }

        hlf.sleep(2);

    }

    void resumenPartida() {
        hlf.sleep(1);
        System.out.println("\n\nRESUMEN DE LA PARTIDA");
        System.out.println("=====================");
        System.out.println("Se han realizado " + hlf.getTotalDisparos() + " disparos.");
        System.out.print("Has acertado " + hlf.getContadorAciertosJugador() + " disparos de los ");
        System.out.println(hlf.getDisparosJugador() + " que has realizados.");
        System.out.print("La máquina ha disparado " + hlf.getDisparosMáquina());
        System.out.println(" veces, de las cuales ha acertado " + hlf.getContadorAciertosMáquina() + ".\n");
        hlf.sleep(1);
        System.out.println("\nAsí han quedado los tableros.\n");
        hlf.sleep(0.5);
        System.out.println("Tus tableros.");
        this.mostrarTableros(hlf.getTableroFlotaJugador(), hlf.getTableroDisparosJugador());
        hlf.sleep(0.5);
        System.out.println("\n\nTableros de la máquina");
        this.mostrarTableros(hlf.getTableroFlotaMáquina(), hlf.getTableroDisparosMáquina());
    }
    
    void mostrarTablero(Estados[][] tablero) {
        System.out.println("    1   2   3   4   5   6   7");
        System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┐");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((char)('A' + i) + " │");
            for (int j = 0; j < tablero[i].length; j++) {
                switch (tablero[i][j]) {
                    case VACIO:
                        System.out.print("   │");
                        break;
                    case AGUA:
                        System.out.print(" ~ │");
                        break;
                    case AGUA_IMP:
                        System.out.print(" ~*│");
                        break;
                    case PORTAAVIONES:
                        System.out.print(" P │");
                        break;
                    case PORTAAVIONES_IMP:
                        System.out.print(" P*│");
                        break;
                    case SUBMARINO:
                        System.out.print(" S │");
                        break;
                    case SUBMARINO_IMP:
                        System.out.print(" S*│");
                        break;
                    case FRAGATA:
                        System.out.print(" F │");
                        break;
                    case FRAGATA_IMP:
                        System.out.print(" F*│");
                        break;
                }
            }
            System.out.println();
            if (i != hlf.TAMAÑO_TABLERO - 1) {
                System.out.println("  ├───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("  └───┴───┴───┴───┴───┴───┴───┘");
    }

    void mostrarTableros(Estados[][] tableroFlota, Estados[][] tableroDisparos) {
        System.out.println("[TABLERO DE FLOTA]" +"\t\t" + "        [TABLERO DE DISPAROS]");
        System.out.println("    1   2   3   4   5   6   7" + "\t\t" + "    1   2   3   4   5   6   7");
        System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┐" + "\t\t" + "  ┌───┬───┬───┬───┬───┬───┬───┐");
        for (int i = 0; i < hlf.TAMAÑO_TABLERO; i++) {
            System.out.print((char)('A' + i) + " │");
            for (int j = 0; j < tableroFlota[i].length; j++) {
                switch (tableroFlota[i][j]) {
                    case VACIO:
                        System.out.print("   │");
                        break;
                    case AGUA:
                        System.out.print(" ~ │");
                        break;
                    case AGUA_IMP:
                        System.out.print(" ~*│");
                        break;
                    case PORTAAVIONES:
                        System.out.print(" P │");
                        break;
                    case PORTAAVIONES_IMP:
                        System.out.print(" P*│");
                        break;
                    case SUBMARINO:
                        System.out.print(" S │");
                        break;
                    case SUBMARINO_IMP:
                        System.out.print(" S*│");
                        break;
                    case FRAGATA:
                        System.out.print(" F │");
                        break;
                    case FRAGATA_IMP:
                        System.out.print(" F*│");
                        break;
                }
            }

            System.out.print("\t\t");
            System.out.print((char)('A' + i) + " │");
            for (int j = 0; j < tableroDisparos[i].length; j++) {
                switch (tableroDisparos[i][j]) {
                    case VACIO:
                        System.out.print("   │");
                        break;
                    case AGUA:
                        System.out.print(" ~ │");
                        break;
                    case AGUA_IMP:
                        System.out.print(" ~*│");
                        break;
                    case PORTAAVIONES:
                        System.out.print(" P │");
                        break;
                    case PORTAAVIONES_IMP:
                        System.out.print(" P*│");
                        break;
                    case SUBMARINO:
                        System.out.print(" S │");
                        break;
                    case SUBMARINO_IMP:
                        System.out.print(" S*│");
                        break;
                    case FRAGATA:
                        System.out.print(" F │");
                        break;
                    case FRAGATA_IMP:
                        System.out.print(" F*│");
                        break;
                }
            }

            System.out.println();
            if (i != hlf.TAMAÑO_TABLERO - 1) {
                System.out.print("  ├───┼───┼───┼───┼───┼───┼───┤");
                System.out.print("\t\t");
                System.out.println("  ├───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.print("  └───┴───┴───┴───┴───┴───┴───┘");
        System.out.print("\t\t");
        System.out.println("  └───┴───┴───┴───┴───┴───┴───┘");
    }

    Punto2D pedirCoordenada() {
        String cord;
        while (!(hlf.coordenadaCorrecta(cord = sc.next().trim().toUpperCase()))) {
            System.out.println("\nLa coordenada [" + cord + "] no es correcta.");
            System.out.println("Por favor, vuelve a escribirla [A-G][1-7]");
        }
        sc.nextLine();
        return hlf.nuevaCoordenada(cord);
    }

    public static void main(String[] args) throws Exception {
        new InterfaceConsola();
    }
}
