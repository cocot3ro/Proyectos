package assets;

public record Punto2D(int fila, int col) {

    public int dist(Punto2D p) {
        return (int) (Math.sqrt(Math.pow((this.fila - p.fila), 2) + Math.pow((this.col - p.col), 2))) + 1;
    }

    @Override
    public String toString() {
        return "[" + (char) (fila + 'A') + (col + 1) + "]";
    }
}
