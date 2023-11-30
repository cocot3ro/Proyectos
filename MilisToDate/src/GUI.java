import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUI extends JFrame {

    private final JTextField txt1;
    private final JTextField txt2;
    private final JTextField txt3;
    private final JButton btn;

    private GUI() {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 100);
        setLocationRelativeTo(null);
        setVisible(true);

        txt1 = new JTextField(20);
        txt2 = new JTextField(20);
        txt3 = new JTextField(20);
        btn = new JButton("Convert");

        btn.addActionListener(actionEvent -> {
            if (txt1.getText().contains(":")) {
                txt2.setText(convertHour(txt1.getText()) + "");
                txt3.setText("");
            } else if (txt1.getText().contains("/")) {
                txt2.setText(convertDate(txt1.getText()) + "");
                txt3.setText("");
            } else {
                txt2.setText(convertDate(Long.parseLong(txt1.getText())));
                txt3.setText(convertHour(Long.parseLong(txt1.getText())));
            }
        });

        add(txt1);
        add(txt2);
        add(txt3);
        add(btn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }

    public Long convertHour(String hora) {
        try {
            DateFormat formatoHora = SimpleDateFormat.getTimeInstance();
            Date fecha = formatoHora.parse(hora);
            return fecha.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public String convertHour(long milisegundos) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Date fecha = new Date(milisegundos);
        return formatoHora.format(fecha);
    }

    public Long convertDate(String fecha) {
        try {
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaObjeto = formatoFecha.parse(fecha);
            return fechaObjeto.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    // Método para convertir milisegundos a una fecha en formato DD:MM:AAAA
    public String convertDate(long milisegundos) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(milisegundos);
        return formatoFecha.format(fecha);
    }
}
