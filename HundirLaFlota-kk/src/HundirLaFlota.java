import assets.Estados;
import assets.Punto2D;

import java.util.Arrays;
import java.util.Random;

public class HundirLaFlota {

    final int TAMANHO_TABLERO;
    final int NUMERO_BARCOS;
    final int TOTAL_OBJETIVOS;
    private final Estados[][] tableroFlotaMaquina;
    // Tablero de los disparos que haces sobre el tablero del otro jugador
    private final Estados[][] tableroDisparosJugador;
    private final Estados[][] tableroDisparosMaquina;
    Random rn = new Random();
    // Tablero de tus barcos
    private Estados[][] tableroFlotaJugador;
    private int contadorAciertosJugador;
    private int contadorAciertosMaquina;

    private int totalDisparos;
    private int disparosJugador;
    private int disparosMaquina;

    HundirLaFlota() {
        this.TAMANHO_TABLERO = 7;
        this.NUMERO_BARCOS = 4;
        this.TOTAL_OBJETIVOS = 12;

        this.tableroFlotaJugador = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];
        this.tableroDisparosJugador = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];
        this.contadorAciertosJugador = 0;

        this.tableroFlotaMaquina = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];
        this.tableroDisparosMaquina = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];
        this.contadorAciertosMaquina = 0;

        this.totalDisparos = 0;
        this.disparosJugador = 0;
        this.disparosMaquina = 0;

        this.inicializarTablero(this.tableroFlotaJugador);
        this.inicializarTablero(this.tableroDisparosJugador);
        this.inicializarTablero(this.tableroFlotaMaquina);
        this.inicializarTablero(this.tableroDisparosMaquina);
    }

    // GETTER
    Estados[][] getTableroFlotaJugador() { // cumple el principio de ocultacion
        Estados[][] temp = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];

        for (int i = 0; i < TAMANHO_TABLERO; i++) {
            System.arraycopy(tableroFlotaJugador[i], 0, temp[i], 0, TAMANHO_TABLERO);
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroFlotaMaquina() { // cumple el principio de ocultacion
        Estados[][] temp = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];

        for (int i = 0; i < TAMANHO_TABLERO; i++) {
            System.arraycopy(tableroFlotaMaquina[i], 0, temp[i], 0, TAMANHO_TABLERO);
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroDisparosJugador() { // cumple el principio de ocultación
        Estados[][] temp = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];

        for (int i = 0; i < TAMANHO_TABLERO; i++) {
            System.arraycopy(tableroDisparosJugador[i], 0, temp[i], 0, TAMANHO_TABLERO);
        }

        return temp;
    }

    // GETTER
    Estados[][] getTableroDisparosMaquina() { // cumple el principio de ocultación
        Estados[][] temp = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];

        for (int i = 0; i < TAMANHO_TABLERO; i++) {
            System.arraycopy(tableroDisparosMaquina[i], 0, temp[i], 0, TAMANHO_TABLERO);
        }

        return temp;
    }

    // GETTER
    int getContadorAciertosJugador() {
        return contadorAciertosJugador;
    }

    // GETTER
    int getContadorAciertosMaquina() {
        return contadorAciertosMaquina;
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
    int getDisparosMaquina() {
        return disparosMaquina;
    }

    private void inicializarTablero(Estados[][] tablero) {
        for (Estados[] estados : tablero) {
            Arrays.fill(estados, Estados.VACIO);
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
        return new Punto2D(rn.nextInt(this.TAMANHO_TABLERO), rn.nextInt(this.TAMANHO_TABLERO));
    }

    // Comprueba que el formato sea correcto ([A-G][1-7])
    boolean coordenadaCorrecta(String cord) {
        return cord.length() == 2 && (cord.charAt(0) >= 'A' && cord.charAt(0) <= 'G') && (cord.charAt(1) >= '1' && cord.charAt(1) <= '7');
    }

    // Resetea el tablero
    void resetTableroFlotaJugador() {
        this.tableroFlotaJugador = new Estados[TAMANHO_TABLERO][TAMANHO_TABLERO];
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

    // Comprueba si la posición donde se quiere colocar un barco ya esta ocupada por
    // otro
    boolean yaHayBarco(Estados[][] tableroFlota, Punto2D p1, Punto2D p2) {
        if (p1.fila() == p2.fila()) { // Si las posiciones en columna son del tipo:
            for (int j = Math.min(p1.col(), p2.col()); j < Math.max(p1.col(), p2.col()) + 1; j++) {
                if (tableroFlota[p1.fila()][j] == Estados.PORTAAVIONES
                        || tableroFlota[p1.fila()][j] == Estados.SUBMARINO
                        || tableroFlota[p1.fila()][j] == Estados.FRAGATA) {
                    return true; // Se devuelve que ya hay barco
                }
            }
        } else { // Si las posiciones en fila son del tipo:
            for (int i = Math.min(p1.fila(), p2.fila()); i < Math.max(p1.fila(), p2.fila()) + 1; i++) {
                if (tableroFlota[i][p1.col()] == Estados.PORTAAVIONES
                        || tableroFlota[i][p1.col()] == Estados.SUBMARINO
                        || tableroFlota[i][p1.col()] == Estados.FRAGATA) {
                    return true; // Se devuelve que ya hay barco
                }
            }
        }
        return false; // Si no se detecta barco se devuelve que no hay nada (posiciones disponibles)
    }

    // Si cumple con todos los requisitos se "coloca" el barco en el tablero de
    // flota del jugador
    void colocarBarcoJugador(Punto2D p1, Punto2D p2, Estados estado) {
        if (p1.fila() == p2.fila()) {
            for (int i = Math.min(p1.col(), p2.col()); i < Math.max(p1.col(), p2.col()) + 1; i++) {
                this.tableroFlotaJugador[p1.fila()][i] = estado;
            }
        } else {
            for (int i = Math.min(p1.fila(), p2.fila()); i < Math.max(p1.fila(), p2.fila()) + 1; i++) {
                this.tableroFlotaJugador[i][p1.col()] = estado;
            }
        }
    }

    // Lo mismo que el anterior pero para los barcos de la maquina
    void colocarBarcoMaquina(Punto2D p1, Punto2D p2, Estados tipoBarco) {
        if (p1.fila() == p2.fila()) {
            for (int j = Math.min(p1.col(), p2.col()); j < Math.max(p1.col(), p2.col()) + 1; j++) {
                this.tableroFlotaMaquina[p1.fila()][j] = tipoBarco;
            }
        } else {
            for (int i = Math.min(p1.fila(), p2.fila()); i < Math.max(p1.fila(), p2.fila()) + 1; i++) {
                this.tableroFlotaMaquina[i][p1.col()] = tipoBarco;
            }
        }
    }

    // El jugador dispara un torpedo
    // El método comprueba si se ha acertado en un barco y si ya se habia disparado
    // en una coordenada.
    Estados jugadorDisparaTorpedo(Punto2D p) {
        this.totalDisparos++;
        this.disparosJugador++;
        switch (this.tableroDisparosJugador[p.fila()][p.col()]) {
            case VACIO:
            case AGUA:
            case PORTAAVIONES:
            case SUBMARINO:
            case FRAGATA:
                switch (this.tableroFlotaMaquina[p.fila()][p.col()]) {
                    case VACIO:
                    case AGUA:
                        this.tableroFlotaMaquina[p.fila()][p.col()] = Estados.AGUA_IMP;
                        this.tableroDisparosJugador[p.fila()][p.col()] = Estados.AGUA_IMP;
                        return Estados.AGUA;
                    case PORTAAVIONES:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMaquina[p.fila()][p.col()] = Estados.PORTAAVIONES_IMP;
                        this.tableroDisparosJugador[p.fila()][p.col()] = Estados.PORTAAVIONES_IMP;
                        return Estados.PORTAAVIONES;
                    case SUBMARINO:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMaquina[p.fila()][p.col()] = Estados.SUBMARINO_IMP;
                        this.tableroDisparosJugador[p.fila()][p.col()] = Estados.SUBMARINO_IMP;
                        return Estados.SUBMARINO;
                    case FRAGATA:
                        this.contadorAciertosJugador++;
                        this.tableroFlotaMaquina[p.fila()][p.col()] = Estados.FRAGATA_IMP;
                        this.tableroDisparosJugador[p.fila()][p.col()] = Estados.FRAGATA_IMP;
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

    // Lo mismo para la maquina.
    Estados maquinaDisparaTorpedo(Punto2D p) {
        this.totalDisparos++;
        this.disparosMaquina++;
        switch (this.tableroFlotaJugador[p.fila()][p.col()]) {
            case VACIO:
            case AGUA:
                this.tableroFlotaJugador[p.fila()][p.col()] = Estados.AGUA;
                this.tableroDisparosMaquina[p.fila()][p.col()] = Estados.AGUA_IMP;
                return Estados.AGUA;
            case PORTAAVIONES:
                this.tableroFlotaJugador[p.fila()][p.col()] = Estados.PORTAAVIONES_IMP;
                this.tableroDisparosMaquina[p.fila()][p.col()] = Estados.PORTAAVIONES_IMP;
                this.contadorAciertosMaquina++;
                return Estados.PORTAAVIONES;
            case SUBMARINO:
                this.tableroFlotaJugador[p.fila()][p.col()] = Estados.SUBMARINO_IMP;
                this.tableroDisparosMaquina[p.fila()][p.col()] = Estados.SUBMARINO_IMP;
                this.contadorAciertosMaquina++;
                return Estados.SUBMARINO;
            case FRAGATA:
                this.tableroFlotaJugador[p.fila()][p.col()] = Estados.FRAGATA_IMP;
                this.tableroDisparosMaquina[p.fila()][p.col()] = Estados.FRAGATA_IMP;
                this.contadorAciertosMaquina++;
                return Estados.FRAGATA;
            default:
                return null;
        }
    }

    boolean ganaJugador() {
        return this.contadorAciertosJugador == TOTAL_OBJETIVOS;
    }

    boolean ganaMaquina() {
        return this.contadorAciertosMaquina == TOTAL_OBJETIVOS;
    }

    String getNombreBarco(Estados estado) {
        return switch (estado) {
            case PORTAAVIONES -> "portaaviones";
            case SUBMARINO -> "submarino";
            case FRAGATA -> "fragata";
            default -> null;
        };
    }

    int getTamanhoBarco(Estados estado) {
        return switch (estado) {
            case PORTAAVIONES -> 5;
            case SUBMARINO -> 3;
            case FRAGATA -> 2;
            default -> 0;
        };
    }
}
