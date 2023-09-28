package motorBM;

import java.util.Arrays;
import java.util.Random;

public class Buscaminas {
    private final Random rn = new Random();

    private Casilla[][] tablero;

    private boolean partidaEmpezada;
    private boolean esFinPartida;
    private boolean victoria;

    private final int CANTIDAD_MINAS;
    
    public Buscaminas(int[] args) {
        int filas = (args[0] < 0 ? 1 : args[0] > 20 ? 20 : args[0]);
        int columnas = (args[1] < 8 ? 8 : args[1] > 30 ? 30 : args[1]);
        int minas = (args[2] < 0 ? 0 : args[2] > filas * columnas - 1 ? filas * columnas - 1 : args[2]);
        
        this.crearTablero(filas, columnas);
        this.CANTIDAD_MINAS = minas;
        this.inicializarTablero();
    }

    public char[][] getTablero() {
        char[][] tmp = new char[this.tablero.length][this.tablero[0].length];
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                tmp[i][j] = this.tablero[i][j].toChar(esFinPartida, victoria);
            }
        }
        return tmp;
    }

    public boolean esFinPartida() {
        return esFinPartida;
    }

    public boolean partidaEmpezada() {
        return this.partidaEmpezada;
    }

    public boolean esVictoria() {
        return victoria;
    }
    
    public int getCANTIDAD_MINAS() {
        return this.CANTIDAD_MINAS;
    }
    
    private void crearTablero(int filas, int columnas) {
        this.tablero = new Casilla[filas][columnas];
    }

    private void inicializarTablero() {
        for (Casilla[] fila : this.tablero) {
            for (int j = 0; j < fila.length; j++) {
                fila[j] = new Casilla(false);
            }
        }
    }
    
    private void colocarMinas(int[] casilla) {
        for (int i = 0; i < CANTIDAD_MINAS; i++) {
            while (true) {
                int[] casillaAleatoria = {rn.nextInt(tablero.length), rn.nextInt(this.tablero[0].length)};
                if (!esMina(casillaAleatoria)
                        && ((tablero.length * tablero[0].length - CANTIDAD_MINAS < 9)
                                ? !Arrays.equals(casilla, casillaAleatoria)
                                : (!(casillaAleatoria[0] >= casilla[0] - 1 && casillaAleatoria[0] <= casilla[0] + 1)
                                        || !(casillaAleatoria[1] >= casilla[1] - 1 && casillaAleatoria[1] <= casilla[1] + 1)))) {
                    this.tablero[casillaAleatoria[0]][casillaAleatoria[1]] = new Casilla(true);
                    break;
                }
            }
        }
        this.contarMinas();
    }

    private void contarMinas() {
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                if (!this.tablero[i][j].esMina()) {
                    int numMinas = 0;
                    for (int f = Math.max(0, i - 1); f < Math.min(this.tablero.length, i + 2); f++) {
                        for (int c = Math.max(0, j - 1); c < Math.min(this.tablero[f].length, j + 2); c++) {
                            if (this.tablero[f][c].esMina()) {
                                numMinas++;
                            }
                        }
                    }
                    this.tablero[i][j].setMinasAdyacentes(numMinas);
                }
            }
        }
    }

    private boolean esMina(int[] casilla) {
        return this.tablero[casilla[0]][casilla[1]].esMina();
    }

    public boolean coordenadaCorrecta(int[] casilla) {
        return ((casilla[0] >= 0 && casilla[0] <= this.tablero.length - 1)
                && (casilla[1] >= 0 && casilla[1] <= this.tablero[0].length - 1));
    }

    public boolean esVisible(int[] casilla) {
        return this.tablero[casilla[0]][casilla[1]].esVisible();
    }

    public void primeraJugada(int[] casilla) {
        colocarMinas(casilla);
        hacerJugada(casilla);
        this.partidaEmpezada = true;
    }

    public void hacerJugada(int[] casilla) {
        if (!this.tablero[casilla[0]][casilla[1]].esVisible()) {
            if (this.tablero[casilla[0]][casilla[1]].esMina()) {
                this.tablero[casilla[0]][casilla[1]].setActiva();
                this.victoria = false;
                this.esFinPartida = true;
            } else if (this.tablero[casilla[0]][casilla[1]].getMinasAdyacentes() > 0 && !this.tablero[casilla[0]][casilla[1]].tieneBandera()) {
                this.tablero[casilla[0]][casilla[1]].setVisible();
            } else if (this.tablero[casilla[0]][casilla[1]].getMinasAdyacentes() == 0 && !this.tablero[casilla[0]][casilla[1]].tieneBandera()) {
                this.tablero[casilla[0]][casilla[1]].setVisible();
                descubrir(casilla);
            }
        } else if (contarBanderas(casilla) == this.tablero[casilla[0]][casilla[1]].getMinasAdyacentes()) {
            descubrirAdyacentes(casilla);

        }
        this.contarCasillas();
    }

    private void descubrir(int[] casilla) {
        if (tablero[casilla[0]][casilla[1]].getMinasAdyacentes() > 0) {
            tablero[casilla[0]][casilla[1]].setVisible();
        } else {
            tablero[casilla[0]][casilla[1]].setVisible();
            for (int i = Math.max(0, casilla[0] - 1); i < Math.min(this.tablero.length, casilla[0] + 2); i++) {
                for (int j = Math.max(0, casilla[1] - 1); j < Math.min(this.tablero[i].length, casilla[1] + 2); j++) {
                    if (!(i == casilla[0] && j == casilla[1]) && !this.tablero[i][j].esVisible()) {
                        if (this.tablero[i][j].getMinasAdyacentes() > 0 && !this.tablero[i][j].tieneBandera()) {
                            this.tablero[i][j].setVisible();
                        } else if (this.tablero[i][j].getMinasAdyacentes() == 0 && !this.tablero[i][j].tieneBandera()) {
                            this.tablero[i][j].setVisible();
                            descubrir(new int[]{i, j});
                        }
                    }
                }
            }
        }
    }

    private void descubrirAdyacentes(int[] casilla) {
        for (int i = Math.max(0, casilla[0] - 1); i < Math.min(tablero.length, casilla[0] + 2) && !this.esFinPartida; i++) {
            for (int j = Math.max(0, casilla[1] - 1); j < Math.min(tablero[i].length, casilla[1] + 2) && !this.esFinPartida; j++) {
                if (!tablero[i][j].esVisible()) {
                    if (tablero[i][j].esMina() && !tablero[i][j].tieneBandera()) {
                        this.victoria = false;
                        this.tablero[i][j].setActiva();
                        this.esFinPartida = true;
                    } else if (!tablero[i][j].tieneBandera()) {
                        descubrir(new int[] { i, j });
                    }
                }
            }
        }
    }
    
    private int contarBanderas(int[] casilla) {
        int banderas = 0;
        for (int i = Math.max(0, casilla[0] - 1); i < Math.min(tablero.length, casilla[0] + 2); i++) {
            for (int j = Math.max(0, casilla[1] - 1); j < Math.min(tablero[i].length, casilla[1] + 2); j++) {
                if (tablero[i][j].tieneBandera()) {
                    banderas++;
                }
            }
        }
        return banderas;
    }
    
    private void contarCasillas() {
        int cont = 0;
        for (Casilla[] fila : tablero) {
            for (Casilla casilla : fila) {
                if (!casilla.esVisible()) {
                    cont++;
                }
            }
        }

        if (cont == this.CANTIDAD_MINAS) {
            this.esFinPartida = true;
            this.victoria = true;
        }
    }

    public boolean tieneBandera(int[] casilla) {
        return this.tablero[casilla[0]][casilla[1]].tieneBandera();
    }

    public void cambiarBandera(int[] casilla) {
        if (!this.tablero[casilla[0]][casilla[1]].esVisible()) {
            this.tablero[casilla[0]][casilla[1]].setBandera();
        }
    }

}
