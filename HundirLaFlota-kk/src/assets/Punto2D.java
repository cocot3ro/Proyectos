package assets;

public class Punto2D {
    private int fila;
    private int col;

    public Punto2D(int fila, int col) {
        this.fila = fila;
        this.col = col;
    }

    public int getFila() {
        return fila;
    }

    public int getCol() {
        return col;
    }

    public int dist(Punto2D p) {
        return (int)(Math.sqrt(Math.pow((this.fila - p.fila),2) + Math.pow((this.col - p.col),2))) + 1;
    }

    @Override
    public String toString() {
        return "[" + (char)(fila + 65) + (col + 1) + "]";
    }
}
