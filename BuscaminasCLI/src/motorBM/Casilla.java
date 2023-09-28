package motorBM;

public class Casilla {
    private final boolean ES_MINA;
    private int minasAdyacentes;
    private boolean esVisible;
    private boolean tieneBandera;
    private boolean estaActiva;

    public Casilla(boolean esMina) {
        this.ES_MINA = esMina;
        this.minasAdyacentes = (esMina ? -1 : 0);
        this.esVisible = false;
        this.tieneBandera = false;
        this.estaActiva = false;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public boolean esVisible() {
        return esVisible;
    }

    public void setVisible() {
        this.esVisible = true;
    }

    public boolean tieneBandera() {
        return this.tieneBandera;
    }

    public boolean getTieneBandera() {
        return this.tieneBandera;
    }

    public void setBandera() {
        this.tieneBandera = !this.tieneBandera;
    }

    public boolean esMina() {
        return ES_MINA;
    }

    public void setActiva() {
        this.estaActiva = true;
    }

    public boolean estaActiva() {
        return this.estaActiva;
    }

    public char toChar(boolean esFinPartida, boolean esVictoria) {
        if (!esFinPartida) {
            if (esVisible) {
                return (minasAdyacentes > 0) ? toChar(minasAdyacentes) : ' ';
            }
            return tieneBandera() ? 'F' : '_';
        }

        if (ES_MINA) {
            if (tieneBandera()) {
                return 'F';
            }
            if (estaActiva) {
                return '#';
            }
            return esVictoria ? 'F' : '*';
        }

        if (tieneBandera()) {
            return 'X';
        }
        if (!esVisible) {
            return '_';
        }
        return toChar(minasAdyacentes);
    }

    private char toChar(int n) {
        return (n + "").charAt(0);
    }
}
