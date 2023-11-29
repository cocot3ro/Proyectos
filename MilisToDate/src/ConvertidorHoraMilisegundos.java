import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertidorHoraMilisegundos {

    // Método para convertir una hora en formato HH:mm:ss a milisegundos
    public static void convert(String hora) {
        try {
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            Date fecha = formatoHora.parse(hora);
            System.out.println(hora + " -> " + fecha.getTime() + " ms");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void convert(long milisegundos) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Date fecha = new Date(milisegundos);
        System.out.println(milisegundos + "ms -> " + formatoHora.format(fecha));
    }

    public static void main(String[] args) {
        convert("");
    }
}