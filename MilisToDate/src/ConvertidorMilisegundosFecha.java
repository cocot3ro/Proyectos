import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertidorMilisegundosFecha {

    public static void convert(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaObjeto = formatoFecha.parse(fecha);
            System.out.println(fecha + " -> " + fechaObjeto.getTime() + " ms");
        } catch (ParseException ignore) {

        }
    }

    // Método para convertir milisegundos a una fecha en formato DD:MM:AAAA
    public static String convert(long milisegundos) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(milisegundos);
        System.out.println(milisegundos + "ms -> " + formatoFecha.format(fecha));
        return formatoFecha.format(fecha);
    }

    public static void main(String[] args) {
        convert("30/11/2023");
    }
}