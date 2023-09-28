import java.util.Random;

import assets.*;

public class HundirLaFlota {

    Random rn = new Random();

    final int TAMAÑO_TABLERO;
    final int NUMERO_BARCOS;
    final int TOTAL_OBJETIVOS;

    // Tablero de tus barcos
    private Estados[][] tableroFlotaJugador;
    private Estados[][] tableroFlotaMáquina;

    // Tablero de los disparos que haces sobre el tablero del otro jugador
    private Estados[][] tableroDisparosJugador;
    private Estados[][] tableroDisparosMáquina;

    private int contadorAciertosJugador;
    private int contadorAciertosMáquina;

    private int totalDisparos;
    private int disparosJugador;
    private int disparosMáquina;

    HundirLaFlota() {
        this.TAMAÑO_TABLERO = 7;
        this.NUMERO_BARCOS = 4;
        this.TOTAL_OBJETIVOS = 12;

        this.tableroFlotaJugador = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];
        this.tableroDisparosJugador = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];
        this.contadorAciertosJugador = 0;

        this.tableroFlotaMáquina = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];
        this.tableroDisparosMáquina = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];
        this.contadorAciertosMáquina = 0;

        this.totalDisparos = 0;
        this.disparosJugador = 0;
        this.disparosMáquina = 0;

        this.inicializarTablero(this.tableroFlotaJugador);
        this.inicializarTablero(this.tableroDisparosJugador);
        this.inicializarTablero(this.tableroFlotaMáquina);
        this.inicializarTablero(this.tableroDisparosMáquina);
    }

    // GETTER
    Estados[][] getTableroFlotaJugador() { // cumple el principio de ocultacion
        Estados[][] temp = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];

        for (int i = 0; i < TAMAÑO_TABLERO; i++) {
            for (int j = 0; j < TAMAÑO_TABLERO; j++) {
                temp[i][j] = tableroFlotaJugador[i][j];
            }
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroFlotaMáquina() { // cumple el principio de ocultacion
        Estados[][] temp = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];

        for (int i = 0; i < TAMAÑO_TABLERO; i++) {
            for (int j = 0; j < TAMAÑO_TABLERO; j++) {
                temp[i][j] = tableroFlotaMáquina[i][j];
            }
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroDisparosJugador() { // cumple el principio de ocultación
        Estados[][] temp = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];

        for (int i = 0; i < TAMAÑO_TABLERO; i++) {
            for (int j = 0; j < TAMAÑO_TABLERO; j++) {
                temp[i][j] = tableroDisparosJugador[i][j];
            }
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroDisparosMáquina() { // cumple el principio de ocultación
        Estados[][] temp = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];

        for (int i = 0; i < TAMAÑO_TABLERO; i++) {
            for (int j = 0; j < TAMAÑO_TABLERO; j++) {
                temp[i][j] = tableroDisparosMáquina[i][j];
            }
        }
        return temp;
    }

    // GETTER
    int getContadorAciertosJugador() {
        return contadorAciertosJugador;
    }

    // GETTER
    int getContadorAciertosMáquina() {
        return contadorAciertosMáquina;
    }

    // GETTER
    int getTotalDisparos() {
        return totalDisparos;
    }

    // GETTER
    int getDisparosJugador() {
        return disparosJugador;
    }

    // GETTER
    int getDisparosMáquina() {
        return disparosMáquina;
    }

    private void inicializarTablero(Estados[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = Estados.VACIO;
            }
        }
    }

    // Genera coordenada a partir de un String
    Punto2D nuevaCoordenada(String cord) {
        return new Punto2D(cord.charAt(0) - 65, Integer.parseInt(String.valueOf(cord.charAt(1))) - 1);
    }

    // Genera coordenada a directamente con los valores que se pasan
    Punto2D nuevaCoordenada(int x, int y) {
        return new Punto2D(x, y);
    }

    // Genera uno de los valores de una coordenada.
    int coordenadaAleatoria(int n) {
        return rn.nextInt(n);
    }

    Punto2D coordenadaAleatoria() {
        return new Punto2D(rn.nextInt(this.TAMAÑO_TABLERO), rn.nextInt(this.TAMAÑO_TABLERO));
    }

    // Comprueba que el formato sea correcto ([A-G][1-7])
    boolean coordenadaCorrecta(String cord) {
        if ((cord.length() == 2)
                && (cord.charAt(0) >= 'A' && cord.charAt(0) <= 'G')
                && (cord.charAt(1) >= '1' && cord.charAt(1) <= '7')) {
            return true;
        }
        return false;
    }

    // Resetea el tablero
    void resetTableroFlotaJugador() {
        this.tableroFlotaJugador = new Estados[TAMAÑO_TABLERO][TAMAÑO_TABLERO];
    }

    // Un boolean aleatorio, en este caso sirve para indicar si un barco se situa en
    // una fila o columna
    boolean randomBool() {
        return rn.nextBoolean();
    }

    // Pausa la ejecución durante x segundos
    void sleep(double segundos) {
        try {
            Thread.sleep((long) (segundos * 1000));
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    // Comprueba si la posición donde se quiere colocar un barco ya está ocupada por
    // otro
    boolean yaHayBarco(Estados[][] tableroFlota, Punto2D p1, Punto2D p2) {
        if (p1.getFila() == p2.getFila()) { // Si las posiciones en columna son del tipo:
            for (int j = Math.min(p1.getCol(), p2.getCol()); j < Math.max(p1.getCol(), p2.getCol()) + 1; j++) {
                if (tableroFlota[p1.getFila()][j] == Estados.PORTAAVIONES
                        || tableroFlota[p1.getFila()][j] == Estados.SUBMARINO
                        || tableroFlota[p1.getFila()][j] == Estados.FRAGATA) {
                    return true; // Se devuelve que ya hay barco
                }
            }
        } else { // Si las posiciones en fila son del tipo:
            for (int i = Math.min(p1.getFila(), p2.getFila()); i < Math.max(p1.getFila(), p2.getFila()) + 1; i++) {
                if (tableroFlota[i][p1.getCol()] == Estados.PORTAAVIONES
                        || tableroFlota[i][p1.getCol()] == Estados.SUBMARINO
                        || tableroFlota[i][p1.getCol()] == Estados.FRAGATA) {
                    return true; // Se devuelve que ya hay barco
                }
            }
        }
        return false; // Si no se detecta barco se devuelve que no hay nada (posiciones disponibles)
    }

    // Si cumple con todos los requisitos se "coloca" el barco en el tablero de
    // flota del jugador
    void colocarBarcoJugador(Punto2D p1, Punto2D p2, Estados estado) {
        if (p1.getFila() == p2.getFila()) {
            for (int i = Math.min(p1.getCol(), p2.getCol()); i < Math.max(p1.getCol(), p2.getCol()) + 1; i++) {
                this.tableroFlotaJugador[p1.getFila()][i] = estado;
            }
        } else {
            for (int i = Math.min(p1.getFila(), p2.getFila()); i < Math.max(p1.getFila(), p2.getFila()) + 1; i++) {
                this.tableroFlotaJugador[i][p1.getCol()] = estado;
            }
        }
    }

    // Lo mismo que el anterior pero para los barcos de la máquina
    void colocarBarcoMáquina(Punto2D p1, Punto2D p2, Estados tipoBarco) {
        if (p1.getFila() == p2.getFila()) {
            for (int j = Math.min(p1.getCol(), p2.getCol()); j < Math.max(p1.getCol(), p2.getCol()) + 1; j++) {
                this.tableroFlotaMáquina[p1.getFila()][j] = tipoBarco;
            }
        } else {
            for (int i = Math.min(p1.getFila(), p2.getFila()); i < Math.max(p1.getFila(), p2.getFila()) + 1; i++) {
                this.tableroFlotaMáquina[i][p1.getCol()] = tipoBarco;
            }
        }
    }

    // El jugador dispara un torpedo
    // El método comprueba si se ha acertado en un barco y si ya se habia disparado
    // en una coordenada.
    Estados jugadorDisparaTorpedo(Punto2D p) {
        this.totalDisparos++;
        this.disparosJugador++;
        switch (this.tableroDisparosJugador[p.getFila()][p.getCol()]) {
            case VACIO:
            case AGUA:
            case PORTAAVIONES:
            case SUBMARINO:
            case FRAGATA:
                switch (this.tableroFlotaMáquina[p.getFila()][p.getCol()]) {
                    case VACIO:
                    case AGUA:
                        this.tableroFlotaMáquina[p.getFila()][p.getCol()] = Estados.AGUA_IMP;
                        this.tableroDisparosJugador[p.getFila()][p.getCol()] = Estados.AGUA_IMP;
                        return Estados.AGUA;
                    case PORTAAVIONES:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMáquina[p.getFila()][p.getCol()] = Estados.PORTAAVIONES_IMP;
                        this.tableroDisparosJugador[p.getFila()][p.getCol()] = Estados.PORTAAVIONES_IMP;
                        return Estados.PORTAAVIONES;
                    case SUBMARINO:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMáquina[p.getFila()][p.getCol()] = Estados.SUBMARINO_IMP;
                        this.tableroDisparosJugador[p.getFila()][p.getCol()] = Estados.SUBMARINO_IMP;
                        return Estados.SUBMARINO;
                    case FRAGATA:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMáquina[p.getFila()][p.getCol()] = Estados.FRAGATA_IMP;
                        this.tableroDisparosJugador[p.getFila()][p.getCol()] = Estados.FRAGATA_IMP;
                        return Estados.FRAGATA;
                    default:
                        return null;
                }

            case AGUA_IMP:
                return Estados.AGUA_IMP;

            case PORTAAVIONES_IMP:
                return Estados.PORTAAVIONES_IMP;

            case SUBMARINO_IMP:
                return Estados.SUBMARINO_IMP;

            case FRAGATA_IMP:
                return Estados.FRAGATA_IMP;

            default:
                return null;
        }
    }

    // Lo mismo para la máquina.
    Estados máquinaDisparaTorpedo(Punto2D p) {
        this.totalDisparos++;
        this.disparosMáquina++;
        switch (this.tableroFlotaJugador[p.getFila()][p.getCol()]) {
            case VACIO:
            case AGUA:
                this.tableroFlotaJugador[p.getFila()][p.getCol()] = Estados.AGUA;
                this.tableroDisparosMáquina[p.getFila()][p.getCol()] = Estados.AGUA_IMP;
                return Estados.AGUA;
            case PORTAAVIONES:
                this.tableroFlotaJugador[p.getFila()][p.getCol()] = Estados.PORTAAVIONES_IMP;
                this.tableroDisparosMáquina[p.getFila()][p.getCol()] = Estados.PORTAAVIONES_IMP;
                this.contadorAciertosMáquina++;
                return Estados.PORTAAVIONES;
            case SUBMARINO:
                this.tableroFlotaJugador[p.getFila()][p.getCol()] = Estados.SUBMARINO_IMP;
                this.tableroDisparosMáquina[p.getFila()][p.getCol()] = Estados.SUBMARINO_IMP;
                this.contadorAciertosMáquina++;
                return Estados.SUBMARINO;
            case FRAGATA:
                this.tableroFlotaJugador[p.getFila()][p.getCol()] = Estados.FRAGATA_IMP;
                this.tableroDisparosMáquina[p.getFila()][p.getCol()] = Estados.FRAGATA_IMP;
                this.contadorAciertosMáquina++;
                return Estados.FRAGATA;
            default:
                return null;
        }
    }

    boolean ganaJugador() {
        return (this.contadorAciertosJugador == TOTAL_OBJETIVOS ? true : false);
    }

    boolean ganaMáquina() {
        return (this.contadorAciertosMáquina == TOTAL_OBJETIVOS ? true : false);
    }

    String getNombreBarco(Estados estado) {
        switch (estado) {
            case PORTAAVIONES:
                return "portaaviones";
            case SUBMARINO:
                return "submarino";
            case FRAGATA:
                return "fragata";
            default:
                return null;
        }
    }

    int getTamañoBarco(Estados estado) {
        switch (estado) {
            case PORTAAVIONES:
                return 5;
            case SUBMARINO:
                return 3;
            case FRAGATA:
                return 2;
            default:
                return 0;
        }
    }
}
